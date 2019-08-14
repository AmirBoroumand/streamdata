package com.proofpoint.streamdata.service.impl;

import com.proofpoint.streamdata.exception.ProofpointException;
import com.proofpoint.streamdata.service.ImportService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class ImportServiceImplTest {

    private ImportService importService;

    @Before
    public void setUp() {
        importService = new ImportServiceImpl();
    }

    @Test(expected = ProofpointException.class)
    public void test_getFiles_blankPath() {
        importService.getFiles("");
    }

    @Test(expected = ProofpointException.class)
    public void test_getFiles_nullPath() {
        importService.getFiles(null);
    }

    @Test(expected = ProofpointException.class)
    public void test_getFiles_pathIsNotDirectory() {
        importService.getFiles(RandomStringUtils.random(8, true, true));
    }
}
