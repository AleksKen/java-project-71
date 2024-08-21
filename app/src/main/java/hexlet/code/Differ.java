package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;


public class Differ {
    private static final String STYLISH = "stylish";

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = getMapAfterParsing(filePath1);
        Map<String, Object> map2 = getMapAfterParsing(filePath2);
        LinkedHashMap<String, Description<Object>> descriptionKeys = (LinkedHashMap<String, Description<Object>>)
                TreeOfDifferences.getDescriptionKeys(map1, map2);
        return Formatter.applySelectedFormat(STYLISH, descriptionKeys);
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<String, Object> map1 = getMapAfterParsing(filePath1);
        Map<String, Object> map2 = getMapAfterParsing(filePath2);
        LinkedHashMap<String, Description<Object>> descriptionKeys = (LinkedHashMap<String, Description<Object>>)
                TreeOfDifferences.getDescriptionKeys(map1, map2);
        return Formatter.applySelectedFormat(formatName, descriptionKeys);
    }

    private static String readFileData(Path file) throws Exception {
        return Files.readString(file);
    }

    private static String getFileType(Path file) {
        return FilenameUtils.getExtension(String.valueOf(file));
    }

    private static  Map<String, Object> getMapAfterParsing(String filePath) throws Exception {
        var file = Path.of(filePath);
        return Parser.parseData(readFileData(file), getFileType(file));
    }
}
