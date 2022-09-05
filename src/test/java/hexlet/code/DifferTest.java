package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    private static String filepath1;
    private static String filepath2;
    @BeforeAll
    public static void beforeAll() {
        filepath1 = getFixturePath("file3.yaml");
        filepath2 = getFixturePath("file4.yaml");
    }

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().toString();
    }

    @Test
    void testGenerate() throws Exception {
        String result = Differ.generate(filepath1, filepath2, FormatterFactory.STYLISH_FORMAT);

        String expected = """
                {
                    key1: value1
                  - key2: value2
                  + key2: value2changed
                  - key3: value3
                  + key3: false
                  - key4: deletedValue4
                  + key5: newValue5
                  - key6: 123
                  + key6: 345
                }""";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateWithoutFormat() throws Exception {
        String result = Differ.generate(filepath1, filepath2);

        String expected = """
                {
                    key1: value1
                  - key2: value2
                  + key2: value2changed
                  - key3: value3
                  + key3: false
                  - key4: deletedValue4
                  + key5: newValue5
                  - key6: 123
                  + key6: 345
                }""";

        assertThat(result).isEqualTo(expected);
    }
}
