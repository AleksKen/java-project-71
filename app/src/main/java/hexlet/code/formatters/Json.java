package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Description;

import java.io.IOException;
import java.util.Map;

public class Json {
    public static String getResult(Map<String, Description<Object>> descriptionKeys) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String res = objectMapper.writeValueAsString(descriptionKeys);
        return res;
    }
}
