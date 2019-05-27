package de.linus.sqlparser.model;

public class DatabaseDto {

	private String dbtype;
	private String dbpath;
	private String dbuser;
	private String dbpassword;

	public DatabaseDto(String dbtype, String dbpath, String dbuser, String dbpassword) {
		this.dbtype = dbtype;
		this.setDbpath(dbpath);
		this.setDbuser(dbuser);
		this.setDbpassword(dbpassword);
	}

	public DatabaseDto() {
	}

	public String getDbtype() {
		return dbtype;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

	public String getDbpath() {
		return dbpath;
	}

	public void setDbpath(String dbpath) {
		this.dbpath = dbpath;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
}