package de.linus.sqlparser;

import de.linus.sqlparser.dao.Queries;
import de.linus.sqlparser.handler.InputHandler;
import de.linus.sqlparser.handler.SQLFileHandler;
import de.linus.sqlparser.model.InputFileDto;
import de.linus.sqlparser.parser.DBConnectionStringParser;
import de.linus.sqlparser.util.FileManipulatorUtil;
import de.linus.sqlparser.util.FileWriterUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SqlparserApplication implements CommandLineRunner {

	public static void main(String[] args)

	{
		SpringApplication.run(SqlparserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		long startTime = System.currentTimeMillis();

		//Auslesen der Input Datei -> Erstellung eines Datenobjektes
		InputFileDto inputFileDto = new InputHandler(args[0]).createInputFileDto();

		//Auslesen des DB Pfades
		String dbUrl = new DBConnectionStringParser().getDBConnectionURL(inputFileDto.getDb());

		//Herstellen der DB Verbindung
		Queries queries = new Queries(dbUrl, inputFileDto.getDb().getDbuser(), inputFileDto.getDb().getDbpassword());

		//Liste der SQL Dateienpfade
		List<String> SQLFiles = inputFileDto.getSql().getSqlfilepaths();

		//Erstellen eines Filewriters für die Output Datei
		FileWriterUtil outputWriter = new FileWriterUtil(inputFileDto.getOut().getOutputfilepath());
		outputWriter.writeFile("", false);

		//Verarbeitung der SQL Dateien
		new SQLFileHandler(queries, SQLFiles, outputWriter).processSQLFiles();

		//Löschen der letzten Zeilen in der Output Datei
		new FileManipulatorUtil(inputFileDto.getOut().getOutputfilepath()).deleteEndLine();

		//Runtime Messung
		long endTime = System.currentTimeMillis();
		long runTime = endTime - startTime;
		System.out.println("Runtime: " + runTime + "ms");
	}
}
