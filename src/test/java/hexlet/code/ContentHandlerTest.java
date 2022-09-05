package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ContentHandlerTest {
    private static String jsonContent;
    private static String yamlContent;

    private static Map<String, Object> parseExpectedResult;

    private static ContentHandler contentHandler;

    public static final Integer EXAMPLE_INT_1 = 300;
    public static final Integer EXAMPLE_INT_2 = 1;
    public static final Integer EXAMPLE_INT_3 = 2;
    public static final Integer EXAMPLE_INT_4 = 3;
    public static final Integer EXAMPLE_INT_5 = 4;
    public static final Integer EXAMPLE_INT_6 = 22;
    public static final Integer EXAMPLE_INT_7 = 33;
    public static final Integer EXAMPLE_INT_8 = 44;
    public static final Integer EXAMPLE_INT_9 = 55;

    @BeforeAll
    public static void beforeAll() throws IOException {
        jsonContent = Files.readString(Paths.get(getFixturePath("file1.json")));
        yamlContent = Files.readString(Paths.get(getFixturePath("file2.yaml")));

        contentHandler = new ContentHandlerImp();

        parseExpectedResult = new LinkedHashMap<>();
        parseExpectedResult.put("setting1", "Another value");
        parseExpectedResult.put("setting2", EXAMPLE_INT_1);
        parseExpectedResult.put("setting3", "none");
        parseExpectedResult.put("key2", "value2");
        parseExpectedResult.put("numbers1", Arrays.asList(
                EXAMPLE_INT_2,
                EXAMPLE_INT_3,
                EXAMPLE_INT_4,
                EXAMPLE_INT_5
        ));
        parseExpectedResult.put("numbers2", Arrays.asList(
                EXAMPLE_INT_6,
                EXAMPLE_INT_7,
                EXAMPLE_INT_8,
                EXAMPLE_INT_9
        ));
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
        Map<String, Object> result = contentHandler.handle(jsonContent, "json");

        assertThat(result).isEqualTo(parseExpectedResult);
    }

    @Test
    void testHandleYaml() throws Exception {
        Map<String, Object> result = contentHandler.handle(yamlContent, "yaml");

        assertThat(result).isEqualTo(parseExpectedResult);
    }
}