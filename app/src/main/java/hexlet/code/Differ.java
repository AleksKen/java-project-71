package hexlet.code;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static LinkedHashMap<String, Description<Object>> getDescriptionKeys(Map<String, Object> map1,
                                                                                           Map<String, Object> map2) {
        TreeMap<String, Object> mixture = new TreeMap<>(map1);
        mixture.putAll(map2);
        LinkedHashMap<String, Description<Object>> descriptionKeys = new LinkedHashMap<>();

        mixture.forEach((key, value) -> {
            var valueFromMap1 = map1.get(key);
            String status;
            if (map1.containsKey(key) && (valueFromMap1 == null || !valueFromMap1.equals(value))) {
                status = "updated";
            } else {
                status = !map2.containsKey(key) ? "removed" : (!map1.containsKey(key) ? "added" : "immutable");
            }
            descriptionKeys.put(key, new Description<>(status, valueFromMap1, value));
        });
        return descriptionKeys;
    }

    public static String generate(Path filePath1, Path filePath2, String formatName) throws Exception {
        HashMap<String, Object> map1 = Parser.parseFile(filePath1);
        HashMap<String, Object> map2 = Parser.parseFile(filePath2);
        LinkedHashMap<String, Description<Object>> descriptionKeys = getDescriptionKeys(map1, map2);
        return Formatter.applySelectedFormat(formatName, descriptionKeys);
    }
}
