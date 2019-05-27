package de.linus.sqlparser.handler;

import de.linus.sqlparser.dao.Queries;
import de.linus.sqlparser.util.FileReaderUtil;
import de.linus.sqlparser.util.FileWriterUtil;
import de.linus.sqlparser.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SQLFileHandler {

    private Queries queries;
    private List<String> SQLFiles;
    private FileWriterUtil outputWriter;

    public SQLFileHandler(Queries queries, List<String> SQLFiles, FileWriterUtil outputWriter) {
        this.queries = queries;
        this.SQLFiles = SQLFiles;
        this.outputWriter = outputWriter;
    }


    public void processSQLFiles() {
        //Verarbeitung der SQL Befehle in allen SQL Dateien
        StringUtil su = new StringUtil();
        for (String file : SQLFiles) {
            String fileContent = new FileReaderUtil(file).readFile();
            String[] tempStatements = fileContent.split(";");
            List<String> statements = new ArrayList<>();
            for (String stmt : tempStatements) {
                stmt = stmt.replaceAll("\\s+", " ").trim();
                if (!stmt.matches("\\s+")) {
                    statements.add(stmt);
                }
            }
            for (String stmt : statements) {
                switch (su.getFirstWord(stmt)) {
                    case "CREATE":
                    case "DROP":
                    case "ALTER":
                    case "TRUNCATE":
                        queries.fireExecute(stmt);
                        break;
                    case "INSERT":
                    case "UPDATE":
                    case "DELETE":
                        queries.fireUpdate(stmt);
                        break;
                    case "SELECT":
                        List<String> list = queries.fireQuery (stmt);
                        outputWriter.writeFile(list, true);
                        outputWriter.writeLineBreak();
                        break;
                    default:
                        throw new RuntimeException(
                                "A wild switch exception occured, while switching the first word of the SQL statement: "
                                        + stmt);
                }
            }
        }
    }

    public Queries getQueries() {
        return queries;
    }

    public void setQueries(Queries queries) {
        this.queries = queries;
    }

    public List<String> getSQLFiles() {
        return SQLFiles;
    }

    public void setSQLFiles(List<String> SQLFiles) {
        this.SQLFiles = SQLFiles;
    }

    public FileWriterUtil getOutputWriter() {
        return outputWriter;
    }

    public void setOutputWriter(FileWriterUtil outputWriter) {
        this.outputWriter = outputWriter;
    }
}
