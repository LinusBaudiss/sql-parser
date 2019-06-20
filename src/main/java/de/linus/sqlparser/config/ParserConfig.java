package de.linus.sqlparser.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ParserConfig {

    @JsonProperty
    private String db_type;
    @JsonProperty
    private String db_path;
    @JsonProperty
    private String db_user;
    @JsonProperty
    private String db_password;
    @JsonProperty
    private String output_filepath;
    @JsonProperty
    private String log_filepath;
    @JsonProperty
    private List<String> sqlfiles;

    public String getDb_type() {
        return db_type;
    }

    public void setDb_type(String db_type) {
        this.db_type = db_type;
    }

    public String getDb_path() {
        return db_path;
    }

    public void setDb_path(String db_path) {
        this.db_path = db_path;
    }

    public String getDb_user() {
        return db_user;
    }

    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    public String getOutput_filepath() {
        return output_filepath;
    }

    public void setOutput_filepath(String output_filepath) {
        this.output_filepath = output_filepath;
    }

    public String getLog_filepath() {
        return log_filepath;
    }

    public void setLog_filepath(String log_filepath) {
        this.log_filepath = log_filepath;
    }

    public List<String> getSqlfiles() {
        return sqlfiles;
    }

    public void setSqlfiles(List<String> sqlfiles) {
        this.sqlfiles = sqlfiles;
    }
}
