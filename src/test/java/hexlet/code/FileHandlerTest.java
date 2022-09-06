package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class FileHandlerTest extends TestCase {
    @Test
    void testGetContentJson() throws Exception {
        String result = FileHandler.getContent(JSON_1_PATH);
        String expected = Files.readString(Paths.get(JSON_1_PATH));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testHandleYaml() throws Exception {
        String result = FileHandler.getContent(YAML_2_PATH);
        String expected = Files.readString(Paths.get(YAML_2_PATH));

        assertThat(result).isEqualTo(expected);
    }
}
