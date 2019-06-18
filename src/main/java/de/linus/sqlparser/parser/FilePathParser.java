package de.linus.sqlparser.parser;

import java.util.ArrayList;
import java.util.List;

public class FilePathParser {

	public String createFilePath(String filepath){
		return System.getProperty("user.home") + filepath;
	}
}