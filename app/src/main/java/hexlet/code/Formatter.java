package hexlet.code;

import hexlet.code.formatters.Stylish;
import org.apache.commons.lang3.tuple.Triple;
import java.util.Map;

public class Formatter {
    public static String applySelectedFormat(String format,
                                             Map<String, Triple<String, Object, Object>> descriptionKeys) {
        switch (format) {
            case "stylish" -> {
                return Stylish.getResult(descriptionKeys);
            }
            default -> {
                return "";
            }
        }
    }
}
