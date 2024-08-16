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
                case "changed" -> diff.append("- ").append(key).append(": ").append(map1.get(key)).append("\n")
                    .append("+ ").append(key).append(": ").append(map2.get(key)).append("\n");

                case "deleted" -> diff.append("- ").append(key).append(": ").append(map1.get(key)).append("\n");

                case "added" -> diff.append("+ ").append(key).append(": ").append(map2.get(key)).append("\n");

                case "immutable" -> diff.append("  ").append(key).append(": ").append(map2.get(key)).append("\n");

                default -> throw new RuntimeException();
            }
        });
        return diff.toString();
    }
}
