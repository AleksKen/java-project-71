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
        String expectedDiff = "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n";
        String diff = App.getDifference(filePath1, filePath2);
        assertEquals(expectedDiff, diff);
    }

    @Test
    public void testCall() throws Exception {
        String[] paths = new String[]{"src/test/resources/file1.json", "src/test/resources/file2.json"};
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
            var temp = outContent.toString();
            assertEquals(expectedDiff, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }
}
