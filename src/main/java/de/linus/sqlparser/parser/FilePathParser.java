package de.linus.sqlparser.parser;

import java.util.ArrayList;
import java.util.List;

public class FilePathParser {

	public String createFilePath(String filepath) {
		String[] inputfiledirectories = filepath.split("/");
		List<String> filedirectories = new ArrayList<>();
		StringBuilder filepathBuilder = new StringBuilder();
		for (String s : inputfiledirectories) {
			if (!s.isEmpty()) {
				filedirectories.add(s);
			}
		}
		for (int i = 0; i < filedirectories.size(); i++) {
			filepathBuilder.append(filedirectories.get(i));
			if (i == 0) {
				filepathBuilder.append(":");
			}
			if (i != filedirectories.size() - 1) {
				filepathBuilder.append("\\\\");
			}
		}
		return filepathBuilder.toString();
	}
}