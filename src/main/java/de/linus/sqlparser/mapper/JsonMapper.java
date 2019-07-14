package de.linus.sqlparser.mapper;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.linus.sqlparser.config.ParserConfig;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class JsonMapper {

    private static final Logger logger = LoggerFactory.getLogger(JsonMapper.class);

    public ParserConfig mapParserConfig(String filepath){
        try {
            return new ObjectMapper().readValue(new File(filepath), ParserConfig.class);
        }
        catch(IOException e){
            logger.error("Error while reading the following file " + filepath, e);
            return null;
        }
    }

}
