package com.file.test.util;

import com.file.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFileUtil {

    private static String fullFileName = "data.csv";
    private static String fileName = "data";
    private static String fileExtension = "csv";

    @Test
    public void testCreateFile() {
        File test = FileUtil.createFile(fileName, fileExtension);
        assertTrue(null != test); // File created
    }

    @Test
    public void testGetFileName() {
        String test = FileUtil.getFileName(fullFileName);
        assertEquals(fileName, test);
    }

    @Test
    public void testGetFileExtn() {
        String test = FileUtil.getFileExtn(fullFileName);
        assertEquals(fileExtension, test);
    }

    @Test
    public void testSplitFileName() {
        String[] test = FileUtil.splitFileName(fullFileName);
        assertEquals(fileName, test[0]);
        assertEquals(fileExtension, test[1]);
    }
}
