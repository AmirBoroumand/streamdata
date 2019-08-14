package com.proofpoint.streamdata.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proofpoint.streamdata.model.JsonData;
import com.proofpoint.streamdata.service.PopulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class PopulatorServiceImpl implements PopulatorService {

    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    public PopulatorServiceImpl(ObjectMapper jacksonObjectMapper) {
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    @Override
    public Map<String, Map<Integer, String>> getDataFromJson(List<File> files) throws IOException {
        Map<String, Map<Integer,String>> map = new HashMap<>();

        for (File file : files) {
            JsonData jsonData = jacksonObjectMapper.readValue(file, JsonData.class);

            Map<Integer,String> lineData = null;

            String filename = jsonData.getFileName();

            if (!map.containsKey(filename)) {
                lineData = new TreeMap<>();
                map.put(filename, lineData);
            } else {
                lineData = map.get(filename);
            }

            lineData.put(jsonData.getLineNumber(), jsonData.getLineData());
        }

        return map;
    }

    @Override
    public Map<String, List<String>> getDataFromPlainText(List<File> files) throws IOException {
        Map<String, List<String>> map = new HashMap<>();

        for (File file : files) {
          List<String> lines = Files.readAllLines(Paths.get(file.toURI()), Charset.defaultCharset());
          map.put(file.getName(), lines);
        }

        return map;
    }
}
