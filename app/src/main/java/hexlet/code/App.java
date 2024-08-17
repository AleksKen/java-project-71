package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.nio.file.Paths;
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
        System.out.println(Differ.generate(file1, file2, format));
        return null;
    }

    public static void main(String[] args) {
        CommandLine.call(new App(), args);
    }
}
