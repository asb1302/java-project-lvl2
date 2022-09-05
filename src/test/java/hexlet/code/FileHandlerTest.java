package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class FileHandlerTest {
    private static String jsonFilePath;
    private static String yamlFilePath;

    @BeforeAll
    public static void beforeAll() {
        jsonFilePath = getFixturePath("file1.json");
        yamlFilePath = getFixturePath("file2.yaml");
    }

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().toString();
    }

    @Test
    void testGetContentJson() throws Exception {
        String result = FileHandler.getContent(jsonFilePath);
        String expected = """
                {
                  "setting1": "Another value",
                  "setting2": 300,
                  "setting3": "none",
                  "key2": "value2",
                  "numbers1": [1, 2, 3, 4],
                  "numbers2": [22, 33, 44, 55],
                  "id": null,
                  "default": ["value1", "value2"],
                  "checked": true,
                  "obj1": {
                    "nestedKey": "value",
                    "isNested": true
                  }
                }""";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testHandleYaml() throws Exception {
        String result = FileHandler.getContent(yamlFilePath);

        String expected = """
                setting1: "Another value"
                setting2: 300
                setting3: "none"
                key2: "value2"
                numbers1: [1, 2, 3, 4]
                numbers2: [22, 33, 44, 55]
                id: null
                default: ["value1", "value2"]
                checked: true
                obj1: {
                    "nestedKey": "value",
                    "isNested": true
                  }""";

        assertThat(result).isEqualTo(expected);
    }
}
