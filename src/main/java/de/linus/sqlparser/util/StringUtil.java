package de.linus.sqlparser.util;

public class StringUtil {

	public String getFirstWord(String s) {
		String[] temp = s.split(" ");
		return temp[0].toUpperCase();
	}
}