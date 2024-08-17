package hexlet.code.formatters;

import hexlet.code.Description;

import java.util.Map;

public class Stylish {
    private static final String UPDATED = "updated";
    private static final String REMOVED = "removed";
    private static final String ADDED = "added";
    private static final String IMMUTABLE = "immutable";

    public static String getResult(Map<String, Description<Object>> descriptionKeys) {
        StringBuilder diff = new StringBuilder();
        diff.append("{\n");
        descriptionKeys.forEach((key, descriptionAndValues) -> {
            switch (descriptionAndValues.getStatus()) {
                case UPDATED -> {
                    addToDiff(diff, "-", key, descriptionAndValues.getOldValue());
                    addToDiff(diff, "+", key, descriptionAndValues.getNewValue());
                }
                case REMOVED -> addToDiff(diff, "-", key, descriptionAndValues.getOldValue());
                case ADDED -> addToDiff(diff, "+", key, descriptionAndValues.getNewValue());
                case IMMUTABLE -> addToDiff(diff, " ", key, descriptionAndValues.getNewValue());
                default -> throw new IllegalArgumentException("Unexpected key description: "
                        + descriptionAndValues.getStatus());
            }
        });
        return diff.append("}").toString();
    }

    private static void addToDiff(StringBuilder diff, String prefix, String key, Object value) {
        diff.append("  ").append(prefix).append(" ").append(key).append(": ").append(value).append("\n");
    }
}
