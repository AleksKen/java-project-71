package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final String JSON = "json";
    private static final String YAML = "yml";
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    private static Map<String, Object> parseFileJson(Path file) throws Exception {
        return JSON_MAPPER.readValue(file.toFile(), HashMap.class);
    }

    private static Map<String, Object> parseFileYaml(Path file) throws Exception {
        return YAML_MAPPER.readValue(file.toFile(), HashMap.class);
    }

    public static Map<String, Object> parseFile(Path file) throws Exception {
        return switch (FilenameUtils.getExtension(String.valueOf(file))) {
            case JSON -> parseFileJson(file);
            case YAML -> parseFileYaml(file);
            default -> throw new IllegalStateException("Unexpected file's type: "
                    + FilenameUtils.getExtension(String.valueOf(file)));
        };
    }
}
