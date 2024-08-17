package hexlet.code.formatters;

import hexlet.code.Description;

import java.util.Map;

public class Stylish {
    public static String getResult(Map<String, Description<Object>> descriptionKeys) {
        StringBuilder diff = new StringBuilder();
        descriptionKeys.forEach((key, descriptionAndValues) -> {
            switch (descriptionAndValues.getStatus()) {
                case "updated" -> {
                    addToDiff(diff, "-", key, descriptionAndValues.getOldValue());
                    addToDiff(diff, "+", key, descriptionAndValues.getNewValue());
                }
                case "removed" -> addToDiff(diff, "-", key, descriptionAndValues.getOldValue());
                case "added" -> addToDiff(diff, "+", key, descriptionAndValues.getNewValue());
                case "immutable" -> addToDiff(diff, " ", key, descriptionAndValues.getNewValue());
                default -> throw new IllegalArgumentException("Unexpected key description: "
                        + descriptionAndValues.getStatus());
            }
        });
        return diff.deleteCharAt(diff.length() - 1).toString();
    }

    private static void addToDiff(StringBuilder diff, String prefix, String key, Object value) {
        diff.append(prefix).append(" ").append(key).append(": ").append(value).append("\n");
    }
}
