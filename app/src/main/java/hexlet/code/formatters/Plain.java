package hexlet.code.formatters;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;

public class Plain {
    public static String getResult(Map<String, Triple<String, Object, Object>> descriptionKeys) {
        StringBuilder diff = new StringBuilder();
        descriptionKeys.forEach((key, descriptionAndValues) -> {
            switch (descriptionAndValues.getLeft()) {
                case "updated" -> addToDiff(diff, key,
                        descriptionAndValues.getLeft(),
                        descriptionAndValues.getMiddle(),
                        descriptionAndValues.getRight());
                case "removed" -> {
                    addToDiff(diff, key, descriptionAndValues.getLeft());
                    diff.append("\n");
                }
                case "added" -> addToDiff(diff, key, descriptionAndValues.getLeft(), descriptionAndValues.getRight());
                case "immutable" -> { }
                default -> throw new IllegalArgumentException("Unexpected key description: "
                        + descriptionAndValues.getLeft());
            }
        });
        return diff.deleteCharAt(diff.length() - 1).toString();
    }

    private static void addToDiff(StringBuilder diff, String key, String status) {
        diff.append("Property '")
                .append(key)
                .append("' was ")
                .append(status);
    }

    private static void addToDiff(StringBuilder diff, String key, String status, Object value) {
        addToDiff(diff, key, status);
        diff.append(" with value: ")
                .append(prepareValue(value))
                .append("\n");
    }

    private static void addToDiff(StringBuilder diff, String key, String status, Object oldValue, Object newValue) {
        addToDiff(diff, key, status);
        diff.append(". From ")
                .append(prepareValue(oldValue))
                .append(" to ")
                .append(prepareValue(newValue))
                .append("\n");
    }

    private static String prepareValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value.getClass() == String.class) {
            return  "'" + value + "'";
        }
        if (!ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
            return "[complex value]";
        }
        return value.toString();
    }
}
