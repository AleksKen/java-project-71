package hexlet.code.formatters;

import hexlet.code.Description;
import org.apache.commons.lang3.ClassUtils;

import java.util.Map;

public class Plain {
    private static final String UPDATED = "updated";
    private static final String REMOVED = "removed";
    private static final String ADDED = "added";
    private static final String IMMUTABLE = "immutable";

    public static String getResult(Map<String, Description<Object>> descriptionKeys) {
        StringBuilder diff = new StringBuilder();
        descriptionKeys.forEach((key, descriptionAndValues) -> {
            switch (descriptionAndValues.getStatus()) {
                case UPDATED -> addToDiff(diff, key,
                        descriptionAndValues.getStatus(),
                        descriptionAndValues.getOldValue(),
                        descriptionAndValues.getNewValue());
                case REMOVED -> {
                    addToDiff(diff, key, descriptionAndValues.getStatus());
                    diff.append("\n");
                }
                case ADDED -> addToDiff(diff,
                        key,
                        descriptionAndValues.getStatus(),
                        descriptionAndValues.getNewValue());
                case IMMUTABLE -> { }
                default -> throw new IllegalArgumentException("Unexpected key description: "
                        + descriptionAndValues.getStatus());
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
