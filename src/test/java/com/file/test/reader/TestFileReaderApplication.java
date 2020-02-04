package com.file.test.reader;

import com.file.reader.FileReaderApplication;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.assertTrue;

public class TestFileReaderApplication {

    @Test
    public void testFileRead() throws IOException {
        FileReaderApplication.main(new String[] { "data.csv" });
        assertTrue(true); // If reached here then success
    }

    @Test
    public void testFileReadEncrypted() throws IOException {
        FileReaderApplication.main(new String[] { "data-enc.csv", "ENC_AES128" });
        assertTrue(true); // If reached here then success
    }

    @Test
    public void testFileReadCompressed() throws IOException {
        FileReaderApplication.main(new String[] { "data.gz", "com_gzip" });
        assertTrue(true); // If reached here then success
    }

    @Test
    public void testFileReadEncryptedCompressed() throws IOException {
        FileReaderApplication.main(new String[] { "data-enc.gz", "ENC_AES128", "com_gzip" });
        assertTrue(true); // If reached here then success
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFileRead_NoArg() throws IOException {
        FileReaderApplication.main(new String[] {});
    }

    @Test(expected = NoSuchFileException.class)
    public void testFileRead_WrongFileName() throws IOException {
        FileReaderApplication.main(new String[] { "sai.csv" });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFileRead_WrongArgCount() throws IOException {
        FileReaderApplication.main(new String[] { "data-enc.gz", "ENC_AES128", "com_gzip", "wrong_arg" });
    }

}
