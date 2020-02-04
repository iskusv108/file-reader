package com.file.util;

import java.io.File;
import java.util.Arrays;

import static com.file.constants.ApplicationConstants.BASE_PATH;

public class FileUtil {

    public static File createFile(String fileName, String extension) {
        return new File(BASE_PATH + fileName + "." + extension );
    }

    public static String getFileName(String fullFileName) {
        String[] split = splitFileName(fullFileName);
        return split[0];
    }

    public static String getFileExtn(String fullFileName) {
        String[] split = splitFileName(fullFileName);
        return split[1];
    }

    public static String[] splitFileName(String fullFileName) {
        return Arrays.stream(fullFileName.split("\\."))
                .map(String::trim)
                .toArray(String[]::new);
    }
}
