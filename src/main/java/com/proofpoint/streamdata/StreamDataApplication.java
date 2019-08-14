package com.proofpoint.streamdata;

import com.proofpoint.streamdata.service.FileWriterService;
import com.proofpoint.streamdata.service.ImportService;
import com.proofpoint.streamdata.service.PopulatorService;
import com.proofpoint.streamdata.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class StreamDataApplication implements CommandLineRunner {

    private ImportService importService;
    private PopulatorService populatorService;
    private FileWriterService fileWriterService;
    private ValidatorService validatorService;

    @Value("${directory.incoming}")
    private String importFilesPath;

    @Value("${directory.reconstructed}")
    private String reconstructedFilesPath;

    @Value("${directory.results}")
    private String resultFilesPath;

    @Autowired
    public StreamDataApplication(ImportService importService, PopulatorService populatorService, FileWriterService fileWriterService, ValidatorService validatorService) {
        this.importService = importService;
        this.populatorService = populatorService;
        this.fileWriterService = fileWriterService;
        this.validatorService = validatorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StreamDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<File> incomingFiles = importService.getFiles(importFilesPath);
        Map<String, Map<Integer,String>> incomingData = populatorService.getDataFromJson(incomingFiles);
        fileWriterService.writeFiles(incomingData);
        validatorService.validate(resultFilesPath, reconstructedFilesPath);
    }
}
