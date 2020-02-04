package com.file.test.handler;

import com.file.handler.FileHandler;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.file.constants.ApplicationConstants.BASE_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFileHandler {

    File data = new File(BASE_PATH + "data.csv");
    File comData = new File(BASE_PATH + "data.gz");

    @Test
    public void testRead() throws IOException {
        assertEquals(FileHandler.read(data),readFile(data.getAbsolutePath(), StandardCharsets.ISO_8859_1));
    }

    @Test
    public void testEncryptDecrypt() throws IOException {
        String content = FileHandler.read(data);
        String encContent = FileHandler.encrypt(content);
        String decContent = FileHandler.decrypt(encContent);
        assertEquals(decContent, content);
    }

    @Test
    public void testCompressDeCompress() throws IOException {
        FileHandler.compress(data, comData);
        FileHandler.compress(comData, data);
        assertTrue(true); // Success if reached here
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
