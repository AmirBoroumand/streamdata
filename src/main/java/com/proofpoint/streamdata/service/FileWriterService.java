package com.proofpoint.streamdata.service;

import java.io.IOException;
import java.util.Map;

public interface FileWriterService {
    /**
     * Writes files based on data in map
     * @param fileData
     * @throws IOException
     */
    void writeFiles(Map<String, Map<Integer,String>> fileData) throws IOException;
}
