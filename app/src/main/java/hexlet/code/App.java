package hexlet.code;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        final CommandLine cmd = new CommandLine(new OptionSection());
        final CommandLine.ParseResult parseResult = cmd.parseArgs(args) ;
        if (!parseResult.errors(). isEmpty()) {
            System.out.println(cmd.getUsageMessage());
        }
        int exitCode = cmd. execute(args);
        System. exit (exitCode);
    }
}
