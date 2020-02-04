package com.file.reader;

import com.file.enums.EncryptCompressEnums;
import com.file.enums.FileExecutionCommands;
import com.file.handler.FileHandler;
import com.file.util.FileUtil;
import com.file.util.LoggingUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static com.file.constants.ApplicationConstants.BASE_PATH;
import static com.file.constants.ApplicationConstants.BASE_PATH_TEST_DATA;

public class FileReaderApplication {

	/**
	 * Main method to read file, decrypt an encrypted file and decompress a compressed file based onn args
	 * @param args
	 * @return
	 */
	public static void main(String... args) throws IOException {

		// Pre Process
		// preProcess();

		// Validate
		validate(args);

		// Process
		List<String> encryptList = Arrays.asList(EncryptCompressEnums.Encrypt.values())
				.stream()
				.map(e -> e.name())
				.collect(Collectors.toList());
		List<String> compressList = Arrays.asList(EncryptCompressEnums.Compress.values())
				.stream()
				.map(e -> e.name())
				.collect(Collectors.toList());
		File inputData = new File(BASE_PATH + args[0]);
		if (inputData == null) {
			LoggingUtil.log(FileExecutionCommands.READ.name(), "Error: File not found", Level.SEVERE);
			throw new NoSuchFileException("Error: File not found");
		}

		// Execute
		// Arg0 - Read
		boolean commandExecuted = false; // To check if any mismatched command is passed
		if(args.length == 1 ) {
			System.out.println("Reading CSV...");
			String content = FileHandler.read(inputData);
			LoggingUtil.log(FileExecutionCommands.READ.name(), "Info: Reading file Completed", Level.INFO);
			System.out.println(content);
			System.out.println("Reading CSV Completed");
			commandExecuted = true;
		}

		// Check if Arg1 matches any of the defined enums related to encryption or compression as they are optional
		if(args.length == 2 && encryptList.stream().anyMatch(args[1]::equalsIgnoreCase)) {
			System.out.println("Reading Encrypted CSV...");
			String content = FileHandler.read(inputData);
			String descContent = FileHandler.decrypt(content);
			LoggingUtil.log(FileExecutionCommands.DECRYPT.name(), "Info: Decrypting file Completed", Level.INFO);
			System.out.println(descContent);
			System.out.println("Reading Encrypted CSV Completed");
			commandExecuted = true;
		}
		if(args.length == 2 && compressList.stream().anyMatch(args[1]::equalsIgnoreCase)) {
			System.out.println("Reading GZ CSV...");
			File decompressData = FileUtil.createFile("data", "csv");
			FileHandler.deCompress(inputData, decompressData);
			LoggingUtil.log(FileExecutionCommands.DECOMPRESS.name(), "Info: Decompress file Completed", Level.INFO);
			String content = FileHandler.read(decompressData);
			System.out.println(content);
			System.out.println("Reading GZ CSV Completed");
			commandExecuted = true;
		}
		// All the args are present
		if(args.length == 3) {
			System.out.println("Reading Encrypted GZ CSV...");
			File encData = FileUtil.createFile("data-enc", "csv");
			FileHandler.deCompress(inputData, encData);
			LoggingUtil.log(FileExecutionCommands.DECOMPRESS.name(), "Info: Decompress file Completed", Level.INFO);
			String content = FileHandler.read(encData);
			String descContent = FileHandler.decrypt(content);
			LoggingUtil.log(FileExecutionCommands.DECRYPT.name(), "Info: Decrypting file Completed", Level.INFO);
			System.out.println(descContent);
			System.out.println("Reading Encrypted GZ CSV Completed");
			commandExecuted = true;
		}

		if(commandExecuted) {
			System.out.println("Completed");
		} else {
			LoggingUtil.log(FileExecutionCommands.READ.name(), "Error: Wrong Argument is passed", Level.SEVERE);
			throw new IllegalArgumentException("Error: Wrong Argument is passed");
		}
    }

	/**
	 * Method for Pre processig i.e; to generate various formats of files
	 * @return
	 */
	private static void preProcess() throws IOException {
		File data = new File(BASE_PATH_TEST_DATA + "data.csv");
		File encData = new File(BASE_PATH_TEST_DATA + "data-enc.csv");
		File encCompress = new File(BASE_PATH_TEST_DATA + "data-enc.gz");
		File compress = new File(BASE_PATH_TEST_DATA + "data.gz");

		// Read
		String content = FileHandler.read(data);

		// Encrypt
		String encContent = FileHandler.encrypt(content);
		LoggingUtil.log(FileExecutionCommands.ENCRYPT.name(), "Info: Encrypting file Completed", Level.INFO);

		// Save encrypted
		FileHandler.saveContent(encData, encContent);
		LoggingUtil.log(FileExecutionCommands.SAVE.name(), "Info: Saving file Completed", Level.INFO);

		// Zip File
		FileHandler.compress(data, compress);
		LoggingUtil.log(FileExecutionCommands.COMPRESS.name(), "Info: Compress file Completed", Level.INFO);
		
		// Zip encrypted File
		FileHandler.compress(encData, encCompress);
		LoggingUtil.log(FileExecutionCommands.COMPRESS.name(), "Info: Compress file Completed", Level.INFO);
	}

	/**
	 * Method to validate if args are passed or not from the command line
	 * @param args
	 * @return
	 */
	private static void validate(String... args) {
		// First check to see if the program was run with the command line argument
		if(args.length < 1) {
			LoggingUtil.log(FileExecutionCommands.READ.name(), "Error: Usage-> java ClassName input file", Level.SEVERE);
			throw new IllegalArgumentException("Error: Usage-> java ClassName input file");
		}
	}

}
