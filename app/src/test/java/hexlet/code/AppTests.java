package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

public class AppTests {
    @Test
    public void testGetDiffJson() throws Exception {
        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");
        String expectedDiff = "  chars1: [a, b, c]\n"
                + "- chars2: [d, e, f]\n"
                + "+ chars2: false\n"
                + "- checked: false\n"
                + "+ checked: true\n"
                + "- default: null\n"
                + "+ default: [value1, value2]\n"
                + "- id: 45\n"
                + "+ id: null\n"
                + "- key1: value1\n"
                + "+ key2: value2\n"
                + "  numbers1: [1, 2, 3, 4]\n"
                + "- numbers2: [2, 3, 4, 5]\n"
                + "+ numbers2: [22, 33, 44, 55]\n"
                + "- numbers3: [3, 4, 5]\n"
                + "+ numbers4: [4, 5, 6]\n"
                + "+ obj1: {nestedKey=value, isNested=true}\n"
                + "- setting1: Some value\n"
                + "+ setting1: Another value\n"
                + "- setting2: 200\n"
                + "+ setting2: 300\n"
                + "- setting3: true\n"
                + "+ setting3: none\n";
        String diff = Formatter.getDifferenceStylish(filePath1, filePath2);
        assertEquals(expectedDiff, diff);
    }

    @Test
    public void testGetDiffYaml() throws Exception {
        Path filePath1 = Path.of("src/test/resources/file1.yml");
        Path filePath2 = Path.of("src/test/resources/file2.yml");
        String expectedDiff = "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n";
        String diff = Formatter.getDifferenceStylish(filePath1, filePath2);
        assertEquals(expectedDiff, diff);
    }

    @Test
    public void testCallJson() {
        String[] paths = new String[]{"src/test/resources/file1.yml", "src/test/resources/file2.yml"};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String expectedDiff = "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n\n";
        try {
            CommandLine.call(new App(), paths);
            assertEquals(expectedDiff, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testCallYaml() {
        String[] paths = new String[]{"src/test/resources/file1.yml", "src/test/resources/file2.yml"};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String expectedDiff = "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n\n";
        try {
            CommandLine.call(new App(), paths);
            assertEquals(expectedDiff, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }
}
