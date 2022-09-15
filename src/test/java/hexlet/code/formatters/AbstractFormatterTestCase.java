package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.TestCase;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

abstract class AbstractFormatterTestCase extends TestCase {
    protected Map<String, Map<String, Object>> getSimpleDiffMap() {
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
        key6Value.put(Differ.OLD_VALUE_KEY, EXAMPLE_INT_1);
        key6Value.put(Differ.NEW_VALUE_KEY, EXAMPLE_INT_2);
        diff.put("key6", key6Value);

        return diff;
    }

    public Map<String, Map<String, Object>> getNullDiffMap() {
        Map<String, Map<String, Object>> diff = new LinkedHashMap<>();

        Map<String, Object> value1 = new LinkedHashMap<>();
        value1.put(Differ.OLD_VALUE_KEY, null);
        value1.put(Differ.NEW_VALUE_KEY, EXAMPLE_INT_1);

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

        return diff;
    }

    public Map<String, Map<String, Object>> getNestedDiffMap() {
        Map<String, Map<String, Object>> diff = new LinkedHashMap<>();

        Map<String, Object> nestedObject = new LinkedHashMap<>();
        nestedObject.put("nestedKey", "value");
        nestedObject.put("isNested", true);

        Map<String, Object> value1 = new LinkedHashMap<>();
        value1.put(Differ.NEW_VALUE_KEY, nestedObject);

        Map<String, Object> value2 = new LinkedHashMap<>();
        value2.put(Differ.OLD_VALUE_KEY, Arrays.asList(EXAMPLE_INT_1, EXAMPLE_INT_2));
        value2.put(Differ.NEW_VALUE_KEY, Arrays.asList(EXAMPLE_INT_3, EXAMPLE_INT_4));

        diff.put("key1", value1);
        diff.put("key2", value2);

        return diff;
    }
}