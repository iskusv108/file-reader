package com.file.test.encryption;

import com.file.encryption.EncryptDecryptString;
import com.file.handler.FileHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.file.constants.ApplicationConstants.BASE_PATH;
import static org.junit.Assert.assertEquals;

public class TestEncryptDecryptString {

    private static String inputString = null;

    @Before
    public void init() throws IOException {
        File data = new File(BASE_PATH + "data.csv");
        inputString = FileHandler.read(data);
    }

    @Test
    public void testEncryptDecryptContent() {
        String encString = EncryptDecryptString.encrypt(inputString);
        String decString = EncryptDecryptString.decrypt(encString);
        assertEquals(decString, inputString);
    }

}
