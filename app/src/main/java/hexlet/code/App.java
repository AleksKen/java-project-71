package hexlet.code;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) throws Exception {
        CommandLine.call(new OptionSection(), args);
    }
}
