package de.linus.sqlparser.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReaderUtil {

	private String filepath;
	private static final Logger LOGGER = Logger.getLogger("FileReaderUtil");
	private String ioexcmsg = "A wild IOException occured, while reading the following file: ";
	private String ioobexcmsg = "A wild IndexOutOfBoundsException occured while checking if the following line is a comment: ";

	public FileReaderUtil(String filepath) {
		this.filepath = filepath;
	}

	public String readFile() {
		StringBuilder content = new StringBuilder();
		Path path = Paths.get(filepath);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (!line.isEmpty()) {
					if (!isComment(line)) {
						content.append(line);
					}
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, ioexcmsg + filepath, e);
		}
		return content.toString();
	}

	public List<String> readFileAsList() {
		List<String> content = new ArrayList<>();
		Path path = Paths.get(filepath);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				content.add(line);
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, ioexcmsg + filepath);
		}
		return content;
	}

	private boolean isComment(String line) {
		try {
			if (line.charAt(0) == '#') {

				return true;
			}
			if (line.substring(0, 2).equals("--")) {
				return true;
			}
		} catch (IndexOutOfBoundsException e) {
			LOGGER.log(Level.SEVERE, ioobexcmsg + line);
		}
		return false;
	}
}