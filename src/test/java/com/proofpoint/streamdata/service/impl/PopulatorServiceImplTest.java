package com.proofpoint.streamdata.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proofpoint.streamdata.model.JsonData;
import com.proofpoint.streamdata.service.PopulatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PopulatorServiceImplTest {

    private PopulatorService populatorService;

    @Mock
    private ObjectMapper mockObjectMapper;

    @Mock
    private JsonData mockJsonData;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.populatorService = new PopulatorServiceImpl(mockObjectMapper);
    }

    @Test
    public void test_getDataFromJson_emptyFileList() throws IOException {
        MockitoAnnotations.initMocks(this);
        Map<String, Map<Integer, String>> result = populatorService.getDataFromJson(Collections.emptyList());
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void test_getDataFromJson_fileList() throws IOException {
        List<File> files = generateFileList();

        Mockito.when(mockJsonData.getFileName()).thenReturn(files.get(0).getName());
        Mockito.when(mockJsonData.getLineNumber()).thenReturn(0);
        Mockito.when(mockJsonData.getLineData()).thenReturn("Lorem ipsum");

        Mockito.when(mockObjectMapper.readValue(files.get(0), JsonData.class)).thenReturn(mockJsonData);

        Map<String, Map<Integer, String>> result = populatorService.getDataFromJson(files);

        String filename = files.get(0).getName();
        Map<Integer, String> map = result.get(filename);

        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.containsKey(filename));

        Assert.assertTrue(map.containsKey(0));
        Assert.assertEquals("Lorem ipsum", map.get(0));
    }

    private List<File> generateFileList() {
        List<File> files = new ArrayList<>();
        files.add(new File("test.txt"));
        return files;
    }



}
