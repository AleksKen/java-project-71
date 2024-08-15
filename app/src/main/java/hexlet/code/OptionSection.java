package hexlet.code;

import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class OptionSection {
        @Parameters(paramLabel = "filepath1", description = "path to first file")
        String filepath1;
        @Parameters(paramLabel = "filepath2", description = "path to second file")
        String filepath2;
        @Option(names = {"-h", "--help"}, description = "Show this help message and exit.", usageHelp=true)
        boolean usageHelpRequested;
        @Option(names = {"-V", "--version"}, description = "Print version information and exit.", versionHelp=true)
        boolean versionInfoRequested;
        @Option(names = {"-f", "--format"}, paramLabel = "format",
                defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
        String format;
}
