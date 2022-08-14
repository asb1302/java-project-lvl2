package hexlet.code.formatters;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//CHECKSTYLE:OFF: checkstyle:magicnumber
class PlaintTest {
    private static Plain plain;

    @BeforeAll
    public static void beforeAll() {
        plain = new Plain();
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

        String result = plain.format(diff);

        String expected = """
                Property 'key2' was updated. From 'value2' to 'value2changed'
                Property 'key3' was updated. From 'value3' to false
                Property 'key4' was removed
                Property 'key5' was added with value: 'newValue5'
                Property 'key6' was updated. From 123 to 345""";

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

        String result = plain.format(diff);

        String expected = """
                Property 'key1' was added with value: [complex value]
                Property 'key2' was updated. From [complex value] to [complex value]""";

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

        Map<String, Object> value4 = new LinkedHashMap<>();
        value4.put(Differ.OLD_VALUE_KEY, null);

        diff.put("key1", value1);
        diff.put("key2", value2);
        diff.put("key3", value3);
        diff.put("key4", value4);

        String result = plain.format(diff);

        String expected = """
                Property 'key1' was updated. From null to 123
                Property 'key3' was updated. From 'value3' to null
                Property 'key4' was removed""";

        assertThat(result).isEqualTo(expected);
    }
}
