package de.linus.sqlparser.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private Connection connection;

	public DatabaseConnection(String url, String user, String pw) throws SQLException {
		if (user.equals(" ") || pw.equals(" ")) {
			connection = DriverManager.getConnection(url);
		} else {
			connection = DriverManager.getConnection(url, user, pw);
		}
	}

	public Connection getConnection() {
		return connection;
	}
}