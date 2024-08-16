package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class AppTests {
    @Test
    public void testGetDiffJson() throws Exception {
        Path filePath1 = Path.of("src/test/resources/input-json/file1.json");
        Path filePath2 = Path.of("src/test/resources/input-json/file2.json");
        String filePathExp = "src/test/resources/expected/expected.txt";
        String expectedDiff = Files.lines(Paths.get(filePathExp))
                .collect(Collectors.joining("\n"));
        String diff = Formatter.getDifferenceStylish(filePath1, filePath2);
        assertEquals(expectedDiff, diff);
    }

    @Test
    public void testGetDiffYaml() throws Exception {
        Path filePath1 = Path.of("src/test/resources/input-yaml/file1.yml");
        Path filePath2 = Path.of("src/test/resources/input-yaml/file2.yml");
        String filePathExp = "src/test/resources/expected/expected.txt";
        String expectedDiff = Files.lines(Paths.get(filePathExp))
                .collect(Collectors.joining("\n"));
        String diff = Formatter.getDifferenceStylish(filePath1, filePath2);
        assertEquals(expectedDiff, diff);
    }

    @Test
    public void testCallJson() throws Exception {
        String[] paths = new String[]{
            "src/test/resources/input-json/file1.json",
            "src/test/resources/input-json/file2.json"
        };
        String filePathExp = "src/test/resources/expected/expected.txt";
        String expectedDiff = Files.lines(Paths.get(filePathExp))
                .collect(Collectors.joining("\n")) + "\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            CommandLine.call(new App(), paths);
            assertEquals(expectedDiff, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testCallYaml() throws Exception {
        String[] paths = new String[]{
            "src/test/resources/input-yaml/file1.yml",
            "src/test/resources/input-yaml/file2.yml"
        };
        String filePathExp = "src/test/resources/expected/expected.txt";
        String expectedDiff = Files.lines(Paths.get(filePathExp))
                .collect(Collectors.joining("\n")) + "\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            CommandLine.call(new App(), paths);
            assertEquals(expectedDiff, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }
}
