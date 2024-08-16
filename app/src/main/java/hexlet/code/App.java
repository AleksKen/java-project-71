package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;

@Command(version = {"@|blue Versioned Command 1.0|@", "@|red,bg(white) (c) 2024|@"},
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true)
public class App implements Callable<Void> {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filepath2;
    @Option(names = {"-f", "--format"}, paramLabel = "format",
            defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    String format;

    @Override
    public Void call() throws Exception {
        Path file1 = Paths.get(filepath1);
        Path file2 = Paths.get(filepath2);
        System.out.println(Formatter.getDifferenceStylish(file1, file2));
        return null;
    }

    public static void main(String[] args) {
        CommandLine.call(new App(), args);
    }

    public static LinkedHashMap<String, String> getDescriptionKeys(HashMap<String, Object> map1,
                                                                   HashMap<String, Object> map2) {
        TreeMap<String, Object> mixture = new TreeMap<>(map1);
        mixture.putAll(map2);
        LinkedHashMap<String, String> descriptionKeys = new LinkedHashMap<>();

        mixture.forEach((key, value) -> {
            var valueFromMap1 = map1.get(key);
            if (map1.containsKey(key) && (valueFromMap1 == null || !valueFromMap1.equals(value))) {
                descriptionKeys.put(key, "changed");
            } else {
                String status = !map2.containsKey(key) ? "deleted" : (!map1.containsKey(key) ? "added" : "immutable");
                descriptionKeys.put(key, status);
            }
        });
        return descriptionKeys;
    }

}
