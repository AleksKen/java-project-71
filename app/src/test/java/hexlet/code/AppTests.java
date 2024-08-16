package hexlet.code;

import org.junit.jupiter.api.Test;

public class AppTests {
    @Test
    public void testGetDiffJson() throws Exception {
        String filePath1 = "/Users/mariakonasova/Hexlet/java-project-71/app/src/main/resources/file1.json";
        String filePath2 = "/Users/mariakonasova/Hexlet/java-project-71/app/src/main/resources/file2.json";
        App.main(new String[]{filePath1, filePath2});
    }
}
