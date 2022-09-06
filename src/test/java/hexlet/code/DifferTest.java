package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest extends TestCase {
    @Test
    void testGenerate() throws Exception {
        String result = Differ.generate(YAML_3_PATH, YAML_4_PATH, FormatterFactory.STYLISH_FORMAT);
        String expected = Files.readString(Paths.get(getFixturePath("file3-file4-diff-stylish.txt")));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateWithoutFormat() throws Exception {
        String result = Differ.generate(YAML_3_PATH, YAML_4_PATH);
        String expected = Files.readString(Paths.get(getFixturePath("file3-file4-diff-stylish.txt")));

        assertThat(result).isEqualTo(expected);
    }
}
