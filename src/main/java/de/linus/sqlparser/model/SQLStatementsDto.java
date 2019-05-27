package de.linus.sqlparser.model;

import java.util.List;

public class SQLStatementsDto {
	
	private List<String> statements;

	public SQLStatementsDto() {}
	
	public SQLStatementsDto(List<String> statements) {
		this.statements = statements;
	}
	
	public List<String> getStatements() {
		return statements;
	}

	public void setStatements(List<String> statements) {
		this.statements = statements;
	}
}