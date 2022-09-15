package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest extends TestCase {
    @Test
    void testGenerate() throws Exception {
        String result = Differ.generate(BEFORE_JSON, BEFORE_YAML, FormatterFactory.STYLISH_FORMAT);
        String expected = Files.readString(Paths.get(getFixturePath("json-yaml-after.txt")));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateWithoutFormat() throws Exception {
        String result = Differ.generate(BEFORE_YAML, BEFORE_JSON);
        String expected = Files.readString(Paths.get(getFixturePath("yaml-json-after.txt")));

        assertThat(result).isEqualTo(expected);
    }
}
