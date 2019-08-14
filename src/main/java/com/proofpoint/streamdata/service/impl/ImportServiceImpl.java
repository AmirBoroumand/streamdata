package com.proofpoint.streamdata.service.impl;

import com.proofpoint.streamdata.exception.ProofpointException;
import com.proofpoint.streamdata.service.ImportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {

    @Override
    public List<File> getFiles(String path) {
        if (StringUtils.isBlank(path)) {
            throw new ProofpointException("The following path is not valid " + path);
        }

        File file = new File(path);

        if (!file.isDirectory()) {
            throw new ProofpointException("The following path is not valid " + file.getAbsolutePath());
        }

        return Arrays.asList(file.listFiles());
    }
}
