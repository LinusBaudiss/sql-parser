package de.linus.sqlparser.model;

import java.util.ArrayList;
import java.util.List;

public class InputFileDto {

	private String[] inputCmds;
	private DatabaseDto db;
	private SQLFilesDto sql;
	private OutputFilesDto out;
	private List<String> sqlFiles;

	public InputFileDto(String[] inputCmds) {
		this.inputCmds = inputCmds;
		this.db = new DatabaseDto();
		this.sql = new SQLFilesDto();
		this.out = new OutputFilesDto();
		this.sqlFiles = new ArrayList<>();
		//Befüllen des Datenobjektes mit konkreten Werten
		fillInputFileDto();
	}

	public void fillInputFileDto() {
		String[] temp;
		db.setDbtype(inputCmds[0]);
		db.setDbpath(inputCmds[1]);
		temp = inputCmds[2].split(":");
		db.setDbuser(temp[1]);
		temp = inputCmds[3].split(":");
		db.setDbpassword(temp[1]);
		out.setOutputfilepath(inputCmds[4]);
		out.setLogfilepath(inputCmds[5]);
		for (int i = 6; i < inputCmds.length; i++) {
			sqlFiles.add(inputCmds[i]);
		}
		sql.setSqlfilepaths(sqlFiles);
	}

	public DatabaseDto getDb() {
		return db;
	}

	public void setDb(DatabaseDto db) {
		this.db = db;
	}

	public SQLFilesDto getSql() {
		return sql;
	}

	public void setSql(SQLFilesDto sql) {
		this.sql = sql;
	}

	public OutputFilesDto getOut() {
		return out;
	}

	public void setOut(OutputFilesDto out) {
		this.out = out;
	}
}