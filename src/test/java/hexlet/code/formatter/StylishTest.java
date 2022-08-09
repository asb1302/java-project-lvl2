package hexlet.code.formatter;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//CHECKSTYLE:OFF: checkstyle:magicnumber
class StylishTest {
    private static Stylish stylish;
    @BeforeAll
    public static void beforeAll() {
        stylish = new Stylish();
    }

    @Test
    void testFormatSimpleValues() {
        Map<String, Map<String, Object>> diff = new LinkedHashMap<>();

        LinkedHashMap<String, Object> key1Value = new LinkedHashMap<>();
        key1Value.put(Differ.OLD_VALUE_KEY, "value1");
        key1Value.put(Differ.NEW_VALUE_KEY, "value1");
        diff.put("key1", key1Value);

        LinkedHashMap<String, Object> key2Value = new LinkedHashMap<>();
        key2Value.put(Differ.OLD_VALUE_KEY, "value2");
        key2Value.put(Differ.NEW_VALUE_KEY, "value2changed");
        diff.put("key2", key2Value);

        LinkedHashMap<String, Object> key3Value = new LinkedHashMap<>();
        key3Value.put(Differ.OLD_VALUE_KEY, "value3");
        key3Value.put(Differ.NEW_VALUE_KEY, false);
        diff.put("key3", key3Value);

        LinkedHashMap<String, Object> key4Value = new LinkedHashMap<>();
        key4Value.put(Differ.OLD_VALUE_KEY, "deletedValue4");
        diff.put("key4", key4Value);

        LinkedHashMap<String, Object> key5Value = new LinkedHashMap<>();
        key5Value.put(Differ.NEW_VALUE_KEY, "newValue5");
        diff.put("key5", key5Value);

        LinkedHashMap<String, Object> key6Value = new LinkedHashMap<>();
        key6Value.put(Differ.OLD_VALUE_KEY, 123);
        key6Value.put(Differ.NEW_VALUE_KEY, 345);
        diff.put("key6", key6Value);

        String result = stylish.format(diff);
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
    void testFormatNestedValues() {
        Map<String, Map<String, Object>> diff = new LinkedHashMap<>();

        Map<String, Object> nestedObject = new LinkedHashMap<>();
        nestedObject.put("nestedKey", "value");
        nestedObject.put("isNested", true);

        diff.put("key1", Map.of(Differ.NEW_VALUE_KEY, nestedObject));
        diff.put("key2", Map.of(Differ.OLD_VALUE_KEY, Arrays.asList(1, 2, 3),
                Differ.NEW_VALUE_KEY, Arrays.asList(3, 4, 5)));

        String result = stylish.format(diff);
        String expected = """
                {
                 + key1: {nestedKey=value, isNested=true}
                 - key2: [1, 2, 3]
                 + key2: [3, 4, 5]
                }""";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFormatNullValues() {
        Map<String, Map<String, Object>> diff = new LinkedHashMap<>();

        Map<String, Object> value1 = new LinkedHashMap<>();
        value1.put(Differ.OLD_VALUE_KEY, null);
        value1.put(Differ.NEW_VALUE_KEY, 123);

        Map<String, Object> value2 = new LinkedHashMap<>();
        value2.put(Differ.OLD_VALUE_KEY, null);
        value2.put(Differ.NEW_VALUE_KEY, null);

        Map<String, Object> value3 = new LinkedHashMap<>();
        value3.put(Differ.OLD_VALUE_KEY, "value3");
        value3.put(Differ.NEW_VALUE_KEY, null);

        diff.put("key1", value1);
        diff.put("key2", value2);
        diff.put("key3", value3);

        String result = stylish.format(diff);
        String expected = """
                {
                 - key1: null
                 + key1: 123
                   key2: null
                 - key3: value3
                 + key3: null
                }""";

        assertThat(result).isEqualTo(expected);
    }
}
