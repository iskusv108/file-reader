package com.file.enums;

public enum FileExecutionCommands {

    READ ("FILE_READ"),
    ENCRYPT ("FILE_ENCRYPT"),
    DECRYPT ("FILE_DECRYPT"),
    COMPRESS ("FILE_COMPRESS"),
    DECOMPRESS ("FILE_DECOMPRESS"),
    SAVE ("FILE_SAVE");

    private final String keyName;
    FileExecutionCommands( String keyName ) { this.keyName = keyName; }
    @Override public String toString() { return keyName; }

}
