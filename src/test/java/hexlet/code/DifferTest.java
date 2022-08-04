package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private static String filepath1;
    private static String filepath2;

    private static String filepath3;
    private static String filepath4;

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().toString();
    }

    @BeforeAll
    public static void beforeAll() {
        filepath1 = getFixturePath("file1.json");
        filepath2 = getFixturePath("file2.json");
        filepath3 = getFixturePath("file3.yaml");
        filepath4 = getFixturePath("file4.yaml");
    }

    @Test
    void testGenerateFlatJson() throws Throwable {
        String result1 = new Differ().generate(filepath1, filepath2, "json");
        String expected1 = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        assertThat(result1).isEqualTo(expected1);

        String result2 = new Differ().generate(filepath2, filepath1, "json");
        String expected2 = """
                {
                 + follow: false
                   host: hexlet.io
                 + proxy: 123.234.53.22
                 - timeout: 20
                 + timeout: 50
                 - verbose: true
                }""";
        assertThat(result2).isEqualTo(expected2);
    }

    @Test
    void testGenerateFlatYaml() throws Throwable {
        String result1 = new Differ().generate(filepath3, filepath4, "yaml");
        String expected1 = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        assertThat(result1).isEqualTo(expected1);

        String result2 = new Differ().generate(filepath4, filepath3, "yaml");
        String expected2 = """
                {
                 + follow: false
                   host: hexlet.io
                 + proxy: 123.234.53.22
                 - timeout: 20
                 + timeout: 50
                 - verbose: true
                }""";
        assertThat(result2).isEqualTo(expected2);
    }
}
