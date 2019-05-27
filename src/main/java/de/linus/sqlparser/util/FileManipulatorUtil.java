package de.linus.sqlparser.util;

import java.util.List;

public class FileManipulatorUtil {

	private String filepath;

	public FileManipulatorUtil(String filepath) {
		this.filepath = filepath;
	}

	public boolean deleteEndLine() {
		List<String> lines = new FileReaderUtil(filepath).readFileAsList();
		return new FileWriterUtil(filepath).writeFile(lines, false);
	}
}