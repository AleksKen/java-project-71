package hexlet.code.formatters;

import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;

public class Stylish {
    public static String getResult(Map<String, Triple<String, Object, Object>> descriptionKeys) {
        StringBuilder diff = new StringBuilder();
        descriptionKeys.forEach((key, descriptionAndValues) -> {
            switch (descriptionAndValues.getLeft()) {
                case "changed" -> {
                    addToDiff(diff, "-", key, descriptionAndValues.getMiddle());
                    addToDiff(diff, "+", key, descriptionAndValues.getRight());
                }
                case "deleted" -> addToDiff(diff, "-", key, descriptionAndValues.getMiddle());
                case "added" -> addToDiff(diff, "+", key, descriptionAndValues.getRight());
                case "immutable" -> addToDiff(diff, " ", key, descriptionAndValues.getRight());
                default -> throw new IllegalArgumentException("Unexpected key description: "
                        + descriptionAndValues.getLeft());
            }
        });
        return diff.deleteCharAt(diff.length() - 1).toString();
    }

    private static void addToDiff(StringBuilder diff, String prefix, String key, Object value) {
        diff.append(prefix).append(" ").append(key).append(": ").append(value).append("\n");
    }
}
