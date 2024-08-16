package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;

@Command(version = {
        "@|blue Versioned Command 1.0|@",
        "@|red,bg(white) (c) 2024|@"},
        name = "gendiff", description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true)
public class OptionSection implements Callable<Void> {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filepath2;
    @Option(names = {"-f", "--format"}, paramLabel = "format",
            defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    String format;

    @Override
    public Void call() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Path file1 = Paths.get(filepath1);
            Path file2 = Paths.get(filepath2);
            TreeMap<String, Object> map1 = mapper.readValue(file1.toFile(), TreeMap.class);
            TreeMap<String, Object> map2 = mapper.readValue(file2.toFile(), TreeMap.class);
            System.out.println(getDifference(map1, map2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getDifference(SortedMap<String, Object> map1, SortedMap<String, Object> map2) {
        TreeMap<String, Object> mixture = new TreeMap<>(map1);
        mixture.putAll(map2);
        StringBuilder diff = new StringBuilder();

        mixture.forEach((key, value) -> {
            var valueFromMap1 = map1.get(key);
            var valueFromMap2 = map2.get(key);
            if (valueFromMap1 != null) {
                if (valueFromMap1.equals(value)) {
                    if (valueFromMap2 == null) {
                        diff.append("- ");
                    }
                    else {
                        diff.append("  ");
                    }
                } else {
                    diff.append("- ").append(key).append(": ").append(valueFromMap1).append("\n");
                    diff.append("+ ");
                }
            } else {
                diff.append("+ ");
            }
            diff.append(key).append(": ").append(value).append("\n");
        });
        return diff.toString();
    }

}
