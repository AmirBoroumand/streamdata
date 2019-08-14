package com.proofpoint.streamdata.service.impl;

import com.proofpoint.streamdata.service.ImportService;
import com.proofpoint.streamdata.service.PopulatorService;
import com.proofpoint.streamdata.service.ValidatorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ValidatorServiceImpl implements ValidatorService {
    private static Logger LOG = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    private final ImportService importService;
    private final PopulatorService populatorService;

    @Autowired
    public ValidatorServiceImpl(ImportService importService, PopulatorService populatorService) {
        this.importService = importService;
        this.populatorService = populatorService;
    }

    @Override
    public void validate(String resultsPath, String reconstructedPath) throws IOException {
        List<File> resultFiles = importService.getFiles(resultsPath);
        Map<String, List<String>> resultsData = populatorService.getDataFromPlainText(resultFiles);

        List<File> reconstructedFiles = importService.getFiles(reconstructedPath);
        Map<String, List<String>> reconstructedData = populatorService.getDataFromPlainText(reconstructedFiles);

        if (resultsData.equals(reconstructedData)) {
            LOG.info("Validation result: PASSED");
        } else {
            LOG.info("Validation result: FAILED");
        }
    }
}
