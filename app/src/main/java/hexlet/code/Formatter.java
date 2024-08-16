package hexlet.code;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Formatter {
    public static String getDifferenceStylish(Path file1, Path file2) throws Exception {
        StringBuilder diff = new StringBuilder();
        HashMap<String, Object> map1 = Parser.parseFile(file1);
        HashMap<String, Object> map2 = Parser.parseFile(file2);
        LinkedHashMap<String, String> descriptionKeys = App.getDescriptionKeys(map1, map2);

        descriptionKeys.forEach((key, description) -> {
            switch (description) {
                case "changed" -> {
                    addToDiff(diff, "-", key, map1.get(key));
                    addToDiff(diff, "+", key, map2.get(key));
                }
                case "deleted" -> addToDiff(diff, "-", key, map1.get(key));
                case "added" -> addToDiff(diff, "+", key, map2.get(key));
                case "immutable" -> addToDiff(diff, " ", key, map2.get(key));
                default -> throw new IllegalArgumentException("Unexpected key description: " + description);
            }
        });
        return diff.deleteCharAt(diff.length() - 1).toString();
    }

    private static void addToDiff(StringBuilder diff, String prefix, String key, Object value) {
        diff.append(prefix).append(" ").append(key).append(": ").append(value).append("\n");
    }
}
