package com.file.compression;

import java.io.File;
import java.io.IOException;

public abstract class FileCompression  {

	public static FileCompression getInstance() {
		return null;
	}
	public abstract void compress(File input, File output) throws IOException;
	public abstract void deCompress(File input, File output) throws IOException;

}
