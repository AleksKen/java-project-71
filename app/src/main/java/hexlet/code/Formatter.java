package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String applySelectedFormat(String format,
                                             Map<String, Description<Object>> descriptionKeys) throws IOException {
        return switch (format) {
            case "stylish" -> Stylish.getResult(descriptionKeys);
            case "plain"  -> Plain.getResult(descriptionKeys);
            case "json" -> Json.getResult(descriptionKeys);
            default -> "";
        };
    }
}
