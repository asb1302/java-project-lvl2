package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private static String filepath1;
    private static String filepath2;

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().toString();
    }

    @BeforeAll
    public static void beforeAll() {
        filepath1 = getFixturePath("file1.json");
        filepath2 = getFixturePath("file2.json");
    }

    @Test
    void testGenerate() throws Throwable {
        String result1 = Differ.generate(filepath1, filepath2);
        String expected1 = "{\n - follow: false\n   host: hexlet.io\n - proxy: 123.234.53.22\n - timeout: 50\n + timeout: 20\n + verbose: true\n}";
        assertThat(result1).isEqualTo(expected1);

        String result2 = Differ.generate(filepath2, filepath1);
        String expected2 = "{\n + follow: false\n   host: hexlet.io\n + proxy: 123.234.53.22\n - timeout: 20\n + timeout: 50\n - verbose: true\n}";
        assertThat(result2).isEqualTo(expected2);
    }
}
