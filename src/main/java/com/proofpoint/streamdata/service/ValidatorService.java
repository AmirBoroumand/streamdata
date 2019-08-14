package com.proofpoint.streamdata.service;

import java.io.IOException;

public interface ValidatorService {
    /**
     * Compares file contents of given directories
     * @param resultsPath
     * @param reconstructedPath
     * @throws IOException
     */
    void validate(String resultsPath, String reconstructedPath) throws IOException;
}
