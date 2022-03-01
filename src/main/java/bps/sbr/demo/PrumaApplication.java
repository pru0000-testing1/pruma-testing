package bps.sbr.demo;

import bps.sbr.demo.services.SBRCommonAllSimpleFileStorageService;
import bps.sbr.demo.services.SBRTempFileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.annotation.Resource;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PrumaApplication implements CommandLineRunner {
	@Resource
	SBRTempFileStorageService storageService;
	@Resource
	SBRCommonAllSimpleFileStorageService sbrCommonAllSimpleFileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(PrumaApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
		sbrCommonAllSimpleFileStorageService.deleteAll();
		sbrCommonAllSimpleFileStorageService.init();
	}
}
