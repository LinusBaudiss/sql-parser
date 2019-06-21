package de.linus.sqlparser.util;

public class DBConnectionStringUtil {

	public String getDBConnectionURL(String db_type, String db_path) {
		StringBuilder url = new StringBuilder();
		url.append("jdbc:");
		url.append(db_type + ":");
		url.append(db_path);
		url.append(";create=true");
		return url.toString();
	}
}