package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//CHECKSTYLE:OFF: checkstyle:magicnumber
class FileHandlerTest {
    private static String jsonFilePath;
    private static String yamlFilePath;

    private static Map<String, Object> parseExpectedResult;

    private static FileHandlerInterface fileHandler;

    @BeforeAll
    public static void beforeAll() {
        jsonFilePath = getFixturePath("file1.json");
        yamlFilePath = getFixturePath("file2.yaml");

        fileHandler = new FileHandler();

        parseExpectedResult = new LinkedHashMap<>();
        parseExpectedResult.put("setting1", "Another value");
        parseExpectedResult.put("setting2", 300);
        parseExpectedResult.put("setting3", "none");
        parseExpectedResult.put("key2", "value2");
        parseExpectedResult.put("numbers1", Arrays.asList(1, 2, 3, 4));
        parseExpectedResult.put("numbers2", Arrays.asList(22, 33, 44, 55));
        parseExpectedResult.put("id", null);
        parseExpectedResult.put("default", Arrays.asList("value1", "value2"));
        parseExpectedResult.put("checked", true);

        Map<String, Object> nestedObject = new LinkedHashMap<>();
        nestedObject.put("nestedKey", "value");
        nestedObject.put("isNested", true);
        parseExpectedResult.put("obj1", nestedObject);
    }

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().toString();
    }

    @Test
    void testHandleJson() throws Exception {
        Map<String, Object> result = fileHandler.handle(jsonFilePath);

        assertThat(result).isEqualTo(parseExpectedResult);
    }

    @Test
    void testHandleYaml() throws Exception {
        Map<String, Object> result = fileHandler.handle(yamlFilePath);

        assertThat(result).isEqualTo(parseExpectedResult);
    }
}
