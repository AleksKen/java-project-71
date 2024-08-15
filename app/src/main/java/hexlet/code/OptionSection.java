package hexlet.code;

import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;

@Command(version = {
        "@|blue Versioned Command 1.0|@",
        "@|red,bg(white) (c) 2024|@"},
        name = "gendiff", description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true)
public class OptionSection {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    File filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    File filepath2;
    @Option(names = {"-f", "--format"}, paramLabel = "format",
            defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    String format;
}
