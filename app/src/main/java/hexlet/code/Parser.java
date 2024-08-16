package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.HashMap;

public class Parser {
    public static HashMap<String, Object> parseFile(Path file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file.toFile(), HashMap.class);
    }
}
