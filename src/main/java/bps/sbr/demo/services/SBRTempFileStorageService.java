package bps.sbr.demo.services;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
public interface SBRTempFileStorageService {
    public void init();
    public void save(MultipartFile file, String filename);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
}
