package com.file.encryption;
import com.file.enums.FileExecutionCommands;
import com.file.util.LoggingUtil;

import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.file.constants.ApplicationConstants.*;

/**
 * Program to Encrypt/Decrypt String Using AES 128 bit Encryption Algorithm
 */
public class EncryptDecryptString {

    /**
     * Method for Encrypt Plain String Data
     * @param plainText
     * @return encryptedText
     */
    public static String encrypt(String plainText) {
        String encryptedText = "";
        try {
            Cipher cipher   = Cipher.getInstance(CIPER_TRANSFERMATION);
            byte[] key      = ENC_KEY.getBytes(CHAR_ENCODING);
            SecretKeySpec secretKey = new SecretKeySpec(key, ALGO);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes(CHAR_ENCODING));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
            LoggingUtil.log(FileExecutionCommands.ENCRYPT.name(), "Error: Encrypt Exception-> "+E.getMessage(), Level.SEVERE);
            System.err.println("Encrypt Exception : "+E.getMessage());
        }
        return encryptedText;
    }

    /**
     * Method For Get encryptedText and Decrypted provided String
     * @param encryptedText
     * @return decryptedText
     */
    public static String decrypt(String encryptedText) {
        String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(CIPER_TRANSFERMATION);
            byte[] key = ENC_KEY.getBytes(CHAR_ENCODING);
            SecretKeySpec secretKey = new SecretKeySpec(key, ALGO);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes(CHAR_ENCODING));
            decryptedText = new String(cipher.doFinal(cipherText), CHAR_ENCODING);

        } catch (Exception E) {
            LoggingUtil.log(FileExecutionCommands.DECRYPT.name(), "Error: Decrypt Exception-> "+E.getMessage(), Level.SEVERE);
            System.err.println("Decrypt Exception : "+E.getMessage());
        }
        return decryptedText;
    }
    
}
