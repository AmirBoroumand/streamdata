package com.proofpoint.streamdata.service.impl;

import com.proofpoint.streamdata.service.FileWriterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileWriterServiceImpl implements FileWriterService {
    private static Logger LOG = LoggerFactory.getLogger(FileWriterServiceImpl.class);

    @Value("${directory.reconstructed}")
    private String reconstructedFilesPath;

    @Override
    public void writeFiles(Map<String, Map<Integer, String>> fileData) throws IOException {
        final Path path = Paths.get(reconstructedFilesPath);

        if(Files.notExists(path)){
            LOG.info("Creating directory for reconstructed files" + path.toAbsolutePath());
            Files.createDirectory(path);
        }

        for (Map.Entry<String, Map<Integer, String>> entry : fileData.entrySet()) {
            String newFilePath = reconstructedFilesPath + File.separator + entry.getKey();
            LOG.info("Reconstructing file " + newFilePath);
            Path file = Paths.get(newFilePath);

            List<String> lines = new ArrayList<>(entry.getValue().values());

            removeTrailingBlankLine(lines);
            Files.write(file, lines, StandardCharsets.UTF_8);
        }
    }

    // Removal of trailing blank line is necessary to prevent Files.write() from appending an additional line
    private void removeTrailingBlankLine(List<String> lines) {
        int lastIndex = lines.size() - 1;
        if (StringUtils.isBlank(lines.get(lastIndex))) {
            lines.remove(lastIndex);
        }
    }
}
