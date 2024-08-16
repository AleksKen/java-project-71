package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.nio.file.Paths;
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
        System.out.println(getDifference(file1, file2));
        return null;
    }

    public static void main(String[] args) throws Exception {
        CommandLine.call(new App(), args);
    }

    public static String getDifference(Path file1, Path file2) throws Exception {
        TreeMap<String, Object> map1 = Parser.parseFile(file1);
        TreeMap<String, Object> map2 = Parser.parseFile(file2);
        TreeMap<String, Object> mixture = new TreeMap<>(map1);
        mixture.putAll(map2);
        StringBuilder diff = new StringBuilder();

        mixture.forEach((key, value) -> {
            var valueFromMap1 = map1.get(key);
            var valueFromMap2 = map2.get(key);
            String prefix = "+ ";
            if (valueFromMap1 != null && !valueFromMap1.equals(value)) {
                diff.append("- ").append(key).append(": ").append(valueFromMap1).append("\n");
            } else {
                prefix = valueFromMap2 == null ? "- " : (valueFromMap1 == null ? "+ " : "  ");
            }
            diff.append(prefix).append(key).append(": ").append(value).append("\n");
        });
        return diff.toString();
    }

}
