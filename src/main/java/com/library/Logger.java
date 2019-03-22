package com.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {

	private static Logger logger;

	public static Logger loggerInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	public void writeLog(String log) {
		try {
			Files.write(Paths.get("myFile.txt"), log.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}

	}

}
