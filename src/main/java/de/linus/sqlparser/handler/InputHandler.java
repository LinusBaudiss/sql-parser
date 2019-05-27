package de.linus.sqlparser.handler;

import de.linus.sqlparser.model.InputFileDto;
import de.linus.sqlparser.parser.FilePathParser;
import de.linus.sqlparser.util.FileReaderUtil;

public class InputHandler {

    private String filePath;

    public InputHandler(String inputFilePath){
        this.filePath = inputFilePath;
    }

    public InputFileDto createInputFileDto(){
        //Bauen des Dateipfades der Input Datei
        String inputDatei = new FilePathParser().createFilePath(filePath);

        //Auslesen der Input Datei
        String inputContent = new FileReaderUtil(inputDatei).readFile();
        String[] inputCmds = inputContent.split(";");

        //Bauen der angegebenen Dateipfade innerhalb der Input Datei
        FilePathParser fpp = new FilePathParser();
        for (int i = 0; i < inputCmds.length; i++) {
            if (!(i == 0 || i == 2 || i == 3)) {
                String filepath = inputCmds[i];
                inputCmds[i] = fpp.createFilePath(filepath);
            }
        }

        //Erstellen und zurückgeben des Datenobjektes
        return new InputFileDto(inputCmds);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}