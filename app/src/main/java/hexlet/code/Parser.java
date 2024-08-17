package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;
import java.util.HashMap;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    private static HashMap<String, Object> parseFileJson(Path file) throws Exception {
        return JSON_MAPPER.readValue(file.toFile(), HashMap.class);
    }

    private static HashMap<String, Object> parseFileYaml(Path file) throws Exception {
        return YAML_MAPPER.readValue(file.toFile(), HashMap.class);
    }

    public static HashMap<String, Object> parseFile(Path file) throws Exception {
        return switch (FilenameUtils.getExtension(String.valueOf(file))) {
            case "json" -> parseFileJson(file);
            case "yml" -> parseFileYaml(file);
            default -> throw new IllegalStateException("Unexpected file's type: "
                    + FilenameUtils.getExtension(String.valueOf(file)));
        };
    }
}
