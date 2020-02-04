package com.file.handler;

import com.file.compression.FileCompression;
import com.file.compression.FileCompressionFactory;
import com.file.encryption.EncryptDecryptString;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.file.constants.ApplicationConstants.COMPRESSION_TYPE_GZ;

public class FileHandler {

    private static FileCompression compression = FileCompressionFactory.getInstance(COMPRESSION_TYPE_GZ);

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String read(File data) throws IOException {
        return readFile(data.getAbsolutePath(), StandardCharsets.ISO_8859_1);
    }

    public static String encrypt(String content) {
        return EncryptDecryptString.encrypt(content);
    }

    public static String decrypt(String encryptedContent) {
        return EncryptDecryptString.decrypt(encryptedContent);
    }

    public static void saveContent(File data, String content) throws IOException {
        Files.write(Paths.get(data.getAbsolutePath()), content.getBytes());
    }

    public static void compress(File data, File compressedData) throws IOException {
        compression.compress(data, compressedData);
    }

    public static void deCompress(File compressedData, File data) throws IOException {
        compression.deCompress(compressedData, data);
    }
}
