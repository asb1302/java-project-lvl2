package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//CHECKSTYLE:OFF: checkstyle:magicnumber
class DifferTest {
    private static Differ differ;
    @BeforeAll
    public static void beforeAll() {
        differ = new Differ();
    }

    @Test
    void testGenerateSimpleValues() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3",
                "key4", "deletedValue4",
                "key6", 123
        );

        Map<String, Object> map2 = Map.of(
                "key1", "value1",
                "key2", "value2changed",
                "key3", false,
                "key5", "newValue5",
                "key6", 345
        );

        String expected  = """
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

        String result = differ.generate(map1, map2, Formatter.STYLISH_FORMAT);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateNestedValues() {
        Map<String, Object> map1 = Map.of(
                "key2", Arrays.asList(1, 2, 3)
        );

        Map<String, Object> nestedObject = new LinkedHashMap<>();
        new LinkedHashMap<>();
        nestedObject.put("nestedKey", "value");
        nestedObject.put("isNested", true);

        Map<String, Object> map2 = Map.of(
                "key1", nestedObject,
                "key2", Arrays.asList(3, 4, 5)
        );

        String result = differ.generate(map1, map2, Formatter.STYLISH_FORMAT);

        String expected = """
                {
                 + key1: {nestedKey=value, isNested=true}
                 - key2: [1, 2, 3]
                 + key2: [3, 4, 5]
                }""";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateNullValues() {
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("key1", null);
        map1.put("key2", null);
        map1.put("key3", "value3");
        map1.put("key4", null);

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("key1", 123);
        map2.put("key2", null);
        map2.put("key3", null);

        String expected = """
                {
                 - key1: null
                 + key1: 123
                   key2: null
                 - key3: value3
                 + key3: null
                 - key4: null
                }""";
        String result = differ.generate(map1, map2, Formatter.STYLISH_FORMAT);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateCheckOrder() {
        Map<String, Object> map1 = Map.of(
                "3Key", "value1",
                "2Key", "value2",
                "1Key", "value3"
        );

        Map<String, Object> map2 = Map.of(
                "3Key", "value1",
                "2Key", "value2",
                "1Key", "value3"
        );

        String expected = """
                {
                   1Key: value3
                   2Key: value2
                   3Key: value1
                }""";
        String result = differ.generate(map1, map2, Formatter.STYLISH_FORMAT);

        assertThat(result).isEqualTo(expected);
    }
}
