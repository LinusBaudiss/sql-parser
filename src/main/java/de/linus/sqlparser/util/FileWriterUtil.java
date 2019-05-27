package de.linus.sqlparser.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWriterUtil {

	private static final Logger LOGGER = Logger.getLogger("FileWriterUtil");

	private String filepath;
	private String errmsg = "A wild IOException occured, while writing the following file: ";

	public FileWriterUtil(String filepath) {
		this.filepath = filepath;
	}

	public boolean writeFile(List<String> list, boolean append) {
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath, append)))) {
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					writer.print(list.get(i));
				} else {
					writer.println(list.get(i));
				}
			}
			return true;
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, errmsg + filepath);
			return false;
		}
	}

	public boolean writeFile(String text, boolean append) {
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath, append)))) {
			writer.print(text);
			return true;
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, errmsg + filepath);
			return false;
		}
	}

	public boolean writeLineBreak() {
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath, true)))) {
			writer.println();
			return true;
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, errmsg + filepath);
			return false;
		}
	}
}