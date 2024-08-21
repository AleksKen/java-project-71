package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final String JSON = "json";
    private static final String YAML = "yml";
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    private static Map<String, Object> parseDataJson(String data) throws Exception {
        return JSON_MAPPER.readValue(data, HashMap.class);
    }

    private static Map<String, Object> parseDataYaml(String data) throws Exception {
        return YAML_MAPPER.readValue(data, HashMap.class);
    }

    public static Map<String, Object> parseData(String data, String type) throws Exception {
        return switch (type) {
            case JSON -> parseDataJson(data);
            case YAML -> parseDataYaml(data);
            default -> throw new IllegalStateException("Unexpected data type: "
                    + type);
        };
    }
}
