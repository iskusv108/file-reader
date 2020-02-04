package com.file.test.compression;

import com.file.compression.FileCompression;
import com.file.compression.FileCompressionFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.file.constants.ApplicationConstants.BASE_PATH;
import static com.file.constants.ApplicationConstants.COMPRESSION_TYPE_GZ;
import static org.junit.Assert.assertTrue;

public class TestGZIPFileCompression {

    File data = new File(BASE_PATH + "data.csv");
    File encData = new File(BASE_PATH + "data-enc.csv");
    File comData = new File(BASE_PATH + "data.gz");
    File encComData = new File(BASE_PATH + "data-enc.gz");
    private static FileCompression compression = FileCompressionFactory.getInstance(COMPRESSION_TYPE_GZ);

    @Test
    public void testGZIPCompress_plainData() throws IOException {
        compression.compress(data, comData);
        assertTrue(true); // If reached here then success
    }

    @Test
    public void testGZIPCompress_encData() throws IOException {
        compression.compress(encData, encComData);
        assertTrue(true); // If reached here then success
    }

    @Test
    public void testGZIPDeCompress_plainData() throws IOException {
        compression.deCompress(comData, data);
        assertTrue(true); // If reached here then success
    }

    @Test
    public void testGZIPDeCompress_encData() throws IOException {
        compression.deCompress(encComData, data);
        assertTrue(true); // If reached here then success
    }
}
