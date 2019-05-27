package de.linus.sqlparser.model;

public class OutputFilesDto {

	private String outputfilepath;
	private String logfilepath;
	
	public OutputFilesDto() {}
	
	public OutputFilesDto(String outputfilepath, String logfilepath) {
		this.setOutputfilepath(outputfilepath);
		this.setLogfilepath(logfilepath);
	}

	public String getOutputfilepath() {
		return outputfilepath;
	}

	public void setOutputfilepath(String outputfilepath) {
		this.outputfilepath = outputfilepath;
	}

	public String getLogfilepath() {
		return logfilepath;
	}

	public void setLogfilepath(String logfilepath) {
		this.logfilepath = logfilepath;
	}	
}