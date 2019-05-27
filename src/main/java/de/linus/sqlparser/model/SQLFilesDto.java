package de.linus.sqlparser.model;

import java.util.ArrayList;
import java.util.List;

public class SQLFilesDto {

	private List<String> sqlfilepaths = new ArrayList<>();

	public SQLFilesDto(List<String> sqlfilepaths) {
		this.sqlfilepaths = sqlfilepaths;
	}

	public SQLFilesDto() {
	}

	public List<String> getSqlfilepaths() {
		return sqlfilepaths;
	}

	public void setSqlfilepaths(List<String> sqlfilepaths) {
		this.sqlfilepaths = sqlfilepaths;
	}
}