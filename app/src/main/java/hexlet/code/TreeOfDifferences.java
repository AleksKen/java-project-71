package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeOfDifferences {
    private static final String UPDATED = "updated";
    private static final String REMOVED = "removed";
    private static final String ADDED = "added";
    private static final String IMMUTABLE = "immutable";

    public static Map<String, Description<Object>> getDescriptionKeys(Map<String, Object> map1,
                                                                      Map<String, Object> map2) {
        TreeMap<String, Object> mixture = new TreeMap<>(map1);
        mixture.putAll(map2);
        LinkedHashMap<String, Description<Object>> descriptionKeys = new LinkedHashMap<>();

        mixture.forEach((key, value) -> {
            var valueFromMap1 = map1.get(key);
            String status;
            if (map1.containsKey(key) && (valueFromMap1 == null || !valueFromMap1.equals(value))) {
                status = UPDATED;
            } else {
                status = !map2.containsKey(key) ? REMOVED : (!map1.containsKey(key) ? ADDED : IMMUTABLE);
            }
            descriptionKeys.put(key, new Description<>(status, valueFromMap1, value));
        });
        return descriptionKeys;
    }
}
