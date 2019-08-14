package com.proofpoint.streamdata.service;

import java.io.File;
import java.util.List;

public interface ImportService {
    /**
     * Given a path, returns a list of File objects
     * @param path
     * @return list of File objects
     */
    List<File> getFiles(String path);
}
