package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    protected static final String FILE_1_YAML = getFixturePath("file1.yaml");
    protected static final String FILE_2_YAML = getFixturePath("file2.yaml");
    protected static final String FILE_1_JSON = getFixturePath("file1.json");
    protected static final String FILE_2_JSON = getFixturePath("file2.json");

    protected static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().toString();
    }

    @Test
    void testGenerate() throws Exception {
        String result1 = Differ.generate(FILE_1_JSON, FILE_2_JSON, FormatterFactory.STYLISH_FORMAT);
        String expected1 = Files.readString(Paths.get(getFixturePath("stylish-result.txt")));
        assertThat(result1).isEqualTo(expected1);

        String result2 = Differ.generate(FILE_1_YAML, FILE_2_YAML, FormatterFactory.STYLISH_FORMAT);
        String expected2 = Files.readString(Paths.get(getFixturePath("stylish-result.txt")));
        assertThat(result2).isEqualTo(expected2);

        String result3 = Differ.generate(FILE_1_JSON, FILE_2_JSON, FormatterFactory.PLAINT_FORMAT);
        String expected3 = Files.readString(Paths.get(getFixturePath("plain-result.txt")));
        assertThat(result3).isEqualTo(expected3);

        String result4 = Differ.generate(FILE_1_YAML, FILE_2_YAML, FormatterFactory.PLAINT_FORMAT);
        String expected4 = Files.readString(Paths.get(getFixturePath("plain-result.txt")));
        assertThat(result4).isEqualTo(expected4);

        String result5 = Differ.generate(FILE_1_JSON, FILE_2_JSON, FormatterFactory.JSON_FORMAT);
        String expected5 = Files.readString(Paths.get(getFixturePath("json-result.json")));
        assertThat(result5).isEqualTo(expected5);

        String result6 = Differ.generate(FILE_1_YAML, FILE_2_YAML, FormatterFactory.JSON_FORMAT);
        String expected6 = Files.readString(Paths.get(getFixturePath("json-result.json")));
        assertThat(result6).isEqualTo(expected6);
    }

    @Test
    void testGenerateWithoutFormat() throws Exception {
        String result = Differ.generate(FILE_1_YAML, FILE_2_YAML);
        String expected = Files.readString(Paths.get(getFixturePath("stylish-result.txt")));

        assertThat(result).isEqualTo(expected);
    }
}
