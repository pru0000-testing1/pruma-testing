package bps.sbr.demo.controllers;

import bps.sbr.demo.payloads.responses.ResponseMessage;
import bps.sbr.demo.models.SBRCommonAllSimple;
import bps.sbr.demo.models.SBRCommonAllSimpleFileInfo;
import bps.sbr.demo.repositories.SBRCommonAllSimpleRepository;
import bps.sbr.demo.services.SBRCommonAllSimpleFileStorageService;
import bps.sbr.demo.utils.Reference;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = Reference.HOSTING_URL)
@RestController
@RequestMapping("/api")
public class SBRCommonAllSimpleController {
    @Autowired
    SBRCommonAllSimpleRepository sbrCommonAllSimpleRepository;
    @Autowired
    SBRCommonAllSimpleFileStorageService sbrCommonAllSimpleFileStorageService;

    @GetMapping("/sbr-common-all-simple")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SBRCommonAllSimple>> getSBRCommonAllSimples(@RequestParam(required = false) String nmperusahaan) {
        try {
            List<SBRCommonAllSimple> sbrCommonAllSimples = new ArrayList<SBRCommonAllSimple>();
            if (nmperusahaan == null)
                sbrCommonAllSimpleRepository.findAll().forEach(sbrCommonAllSimples::add);
            else
                sbrCommonAllSimpleRepository.findByNmperusahaan(nmperusahaan).forEach(sbrCommonAllSimples::add);
            if (sbrCommonAllSimples.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sbrCommonAllSimples, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sbr-common-all-simple/upload")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        List<SBRCommonAllSimple> sbrCommonAllSimples = new ArrayList<SBRCommonAllSimple>();
        if (file.isEmpty()) {
            message = "File empty : " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

        try {
            UUID uuid = UUID.randomUUID();
            String[] list_temp = file.getOriginalFilename().split("\\.");
            String filename = uuid.toString()+"."+list_temp[list_temp.length - 1];
            sbrCommonAllSimpleFileStorageService.save(file, filename);

            String fileName = Reference.SBRTEMP_UPLOAD_SIMPLE_FOLDER+"\\"+filename;
            try {

                sbrCommonAllSimples = new CsvToBeanBuilder(new FileReader(fileName))
                        .withType(SBRCommonAllSimple.class)
                        .withSeparator(';')
                        .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                        .build()
                        .parse();

                sbrCommonAllSimples.forEach(
                        sbrCommonAllSimple -> {
                            sbrCommonAllSimple.setUploaded_datetime(new Date());
                            sbrCommonAllSimple.setUploaded_filename(filename);
                        }
                );

                sbrCommonAllSimpleRepository.saveAll(sbrCommonAllSimples);
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

    @GetMapping("/sbr-common-all-simple/files")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SBRCommonAllSimpleFileInfo>> getListFiles() {
        List<SBRCommonAllSimpleFileInfo> fileInfos = sbrCommonAllSimpleFileStorageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(SBRCommonAllSimpleController.class, "getFile", path.getFileName().toString()).build().toString();
            return new SBRCommonAllSimpleFileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/sbr-common-all-simple/files/{filename:.+}")
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = sbrCommonAllSimpleFileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
