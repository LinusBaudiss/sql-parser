package de.linus.sqlparser.parser;

import de.linus.sqlparser.model.DatabaseDto;

public class DBConnectionStringParser {

	public String getDBConnectionURL(DatabaseDto db) {
		StringBuilder url = new StringBuilder();
		url.append("jdbc:");
		url.append(db.getDbtype() + ":");
		url.append(db.getDbpath());
		url.append(";create=true");
		return url.toString();
	}
}