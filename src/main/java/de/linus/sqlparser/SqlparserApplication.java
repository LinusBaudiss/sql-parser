package de.linus.sqlparser;

import de.linus.sqlparser.config.ParserConfig;
import de.linus.sqlparser.dao.Queries;
import de.linus.sqlparser.handler.SQLFileHandler;
import de.linus.sqlparser.mapper.JsonMapper;
import de.linus.sqlparser.util.DBConnectionStringUtil;
import de.linus.sqlparser.util.FileManipulatorUtil;
import de.linus.sqlparser.util.FileWriterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SqlparserApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SqlparserApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SqlparserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        long startTime = System.currentTimeMillis();

        //Auslesen der Input Datei -> Erstellung eines Datenobjektes
        ParserConfig parserConfig = new JsonMapper().mapParserConfig("input.json");

        //Auslesen des DB Pfades
        String dbUrl = new DBConnectionStringUtil().getDBConnectionURL(parserConfig.getDb_type(), parserConfig.getDb_path());

        //Herstellen der DB Verbindung
        Queries queries = new Queries(dbUrl, parserConfig.getDb_user(), parserConfig.getDb_password());

        //Liste der SQL Dateienpfade
        List<String> SQLFiles = parserConfig.getSqlfiles();

        //Erstellen eines Filewriters für die Output Datei
        FileWriterUtil outputWriter = new FileWriterUtil(parserConfig.getOutput_filepath());
        outputWriter.writeFile("", false);

        //Verarbeitung der SQL Dateien
        new SQLFileHandler(queries, SQLFiles, outputWriter).processSQLFiles();

        //Löschen der letzten Zeilen in der Output Datei
        new FileManipulatorUtil(parserConfig.getOutput_filepath()).deleteEndLine();

        //Runtime Messung
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("Runtime: " + runTime + "ms");
    }
}
