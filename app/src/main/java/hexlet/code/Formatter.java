package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import org.apache.commons.lang3.tuple.Triple;
import java.util.Map;

public class Formatter {
    public static String applySelectedFormat(String format,
                                             Map<String, Triple<String, Object, Object>> descriptionKeys) {
        return switch (format) {
            case "stylish" -> Stylish.getResult(descriptionKeys);
            case "plain"  -> Plain.getResult(descriptionKeys);
            default -> "";
        };
    }
}
