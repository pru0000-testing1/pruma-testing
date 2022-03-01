package bps.sbr.demo.controllers;

import bps.sbr.demo.payloads.responses.ResponseMessage;
import bps.sbr.demo.models.SBRTemp;
import bps.sbr.demo.models.SBRTempFileInfo;
import bps.sbr.demo.repositories.SBRCommonAllSimpleRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.springframework.core.io.Resource;
import bps.sbr.demo.repositories.SBRTempRepository;
import bps.sbr.demo.services.SBRTempFileStorageService;
import bps.sbr.demo.utils.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = Reference.HOSTING_URL)
@RestController
@RequestMapping("/api")
public class SBRTempController {
    @Autowired
    SBRTempRepository sbrTempRepository;
    @Autowired
    SBRCommonAllSimpleRepository sbrCommonAllSimpleRepository;
    @Autowired
    SBRTempFileStorageService sbrTempFileStorageService;

//    private final Path root = Paths.get(Reference.SBRTEMP_UPLOAD_FOLDER);

    @GetMapping("/sbr-temps/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SBRTemp>> getSBRTemps(@RequestParam(required = false) String nmperusahaan) {
        try {
            List<SBRTemp> sbrTemps = new ArrayList<SBRTemp>();
            if (nmperusahaan == null)
                sbrTempRepository.findByIsLockedIsNullAndIdsbrIsNull().forEach(sbrTemps::add);
            else
                sbrTempRepository.findByNmperusahaanContainingAndIsLockedAndIdsbr(nmperusahaan, null, null).forEach(sbrTemps::add);
            if (sbrTemps.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sbrTemps, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sbr-temps/matching")
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getSBRTempsMatching(@RequestParam(required = false) String nmperusahaan) {
        Map<String, Object> response = new HashMap<>();
        try {
            SBRTemp sbrTemp = sbrTempRepository.findTopIdsbrIsNullAndIsLockedIsNullAndByOrderByUploadedDatetimeDesc();
            sbrTemp.setIsLocked(new Date());
            sbrTemp.setPetugas(0);
            sbrTempRepository.save(sbrTemp);
            List<Map<String, Object>> list_sbr = sbrCommonAllSimpleRepository.freeTextNmperusahaanAndAlamatAndKdprop(sbrTemp.getNmperusahaan(), sbrTemp.getAlamat(), sbrTemp.getKdprop());
            response.put("sbr_domain", sbrTemp);
            response.put("sbr_codomain", list_sbr);
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/sbr-temps/matching/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> updateData(@RequestBody SBRTemp updatedSBRTemp, @PathVariable Long id) {
        try {
            return sbrTempRepository.findById(id)
                    .map(sbrTemp -> {
                        sbrTemp.setIdsbr(updatedSBRTemp.getIdsbr());
                        sbrTempRepository.save(sbrTemp);
                        String message = "Sukses matching: " + sbrTemp.getNmperusahaan();
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                    })
                    .orElseGet(() -> {
                        sbrTempRepository.save(updatedSBRTemp);
                        String message = "Sukses matching data baru";
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                    });
        } catch (Exception e) {
            String message = "Terjadi Kesalahan : " + e.toString();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/sbr-temps/upload")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        List<SBRTemp> sbrTemps = new ArrayList<SBRTemp>();
        if (file.isEmpty()) {
            message = "File empty : " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

        try {
            UUID uuid = UUID.randomUUID();
            String[] list_temp = file.getOriginalFilename().split("\\.");
            String filename = uuid.toString()+"."+list_temp[list_temp.length - 1];
            sbrTempFileStorageService.save(file, filename);

            String fileName = Reference.SBRTEMP_UPLOAD_FOLDER+"\\"+filename;
            try {

                sbrTemps = new CsvToBeanBuilder(new FileReader(fileName))
                        .withType(SBRTemp.class)
                        .withSeparator(';')
                        .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                        .build()
                        .parse();

                sbrTemps.forEach(
                        sbrTemp -> {
                            sbrTemp.setUploadedDatetime(new Date());
                            sbrTemp.setUploadedFilename(filename);
                        }
                );

                sbrTempRepository.saveAll(sbrTemps);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "! ("+ e.toString()+")";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/sbr-temps/files")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SBRTempFileInfo>> getListFiles() {
        List<SBRTempFileInfo> fileInfos = sbrTempFileStorageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(SBRTempController.class, "getFile", path.getFileName().toString()).build().toString();
            return new SBRTempFileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/sbr-temps/files/{filename:.+}")
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = sbrTempFileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
