package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class OptionSection {
        @Option(names = {"-h", "--help"}, description = "Show this help message and exit.", usageHelp=true)
        boolean usageHelpRequested;
        @Option(names = {"-V", "--version"}, description = "Print version information and exit.", versionHelp=true)
        boolean versionInfoRequested;
}
