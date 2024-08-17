package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishTests {
    @Test
    public void testGetDiffJson() throws Exception {
        String filePath1 = "src/test/resources/input-json/file1.json";
        String filePath2 = "src/test/resources/input-json/file2.json";
        String filePathExp = "src/test/resources/expected/stylish.txt";
        String expectedDiff = Files.lines(Paths.get(filePathExp))
                .collect(Collectors.joining("\n"));
        String diff = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expectedDiff, diff);
    }

    @Test
    public void testGetDiffYaml() throws Exception {
        String filePath1 = "src/test/resources/input-yaml/file1.yml";
        String filePath2 = "src/test/resources/input-yaml/file2.yml";
        String filePathExp = "src/test/resources/expected/stylish.txt";
        String expectedDiff = Files.lines(Paths.get(filePathExp))
                .collect(Collectors.joining("\n"));
        String diff = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expectedDiff, diff);
    }
}
