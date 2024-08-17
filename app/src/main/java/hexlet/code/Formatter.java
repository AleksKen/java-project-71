package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    public static String applySelectedFormat(String format,
                                             Map<String, Description<Object>> descriptionKeys) throws IOException {
        return switch (format) {
            case STYLISH -> Stylish.getResult(descriptionKeys);
            case PLAIN  -> Plain.getResult(descriptionKeys);
            case JSON -> Json.getResult(descriptionKeys);
            default -> throw new IllegalArgumentException("Unexpected style: " + format);
        };
    }
}
