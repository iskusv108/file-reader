package com.file.compression;

import static com.file.constants.ApplicationConstants.COMPRESSION_TYPE_GZ;

public class FileCompressionFactory {

	public static FileCompression getInstance(String returnType) {
		// Can be formated to Java 8 Optional also
		if(null != returnType && returnType.equalsIgnoreCase(COMPRESSION_TYPE_GZ)) {
			return GZIPFileCompression.getInstance();
		}
		// BZIP can be added in similar way using BZIP2FileCompression.class
		return null;
	}

}
