package com.proofpoint.streamdata.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PopulatorService {

    /**
     * Populates map with key = filename, value = map of data lines given a list of JSON formatted files
     * @param files
     * @return
     * @throws IOException
     */
    Map<String, Map<Integer, String>>  getDataFromJson(List<File> files) throws IOException;

    /**
     * Populates map with key = filename, value = list of data lines given a list of plain text files
     * @param files
     * @return
     * @throws IOException
     */
    Map<String, List<String>>  getDataFromPlainText(List<File> files) throws IOException;
}
