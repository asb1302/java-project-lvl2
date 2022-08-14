package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DiffMakerTest {
    private static DiffMakerInterface diffMaker;
    @BeforeAll
    public static void beforeAll() {
        diffMaker = new DiffMaker();
    }

    public static final Integer EXAMPLE_INT_1 = 123;
    public static final Integer EXAMPLE_INT_2 = 345;
    public static final Integer EXAMPLE_INT_3 = 567;
    public static final Integer EXAMPLE_INT_4 = 789;

    @Test
    void testGenerateSimpleValues() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3",
                "key4", "deletedValue4",
                "key6", EXAMPLE_INT_1
        );

        Map<String, Object> map2 = Map.of(
                "key1", "value1",
                "key2", "value2changed",
                "key3", false,
                "key5", "newValue5",
                "key6", EXAMPLE_INT_2
        );

        Map<String, Map<String, Object>> expected = new LinkedHashMap<>(Map.of(
                "key1", Map.of(Differ.OLD_VALUE_KEY, "value1", Differ.NEW_VALUE_KEY, "value1"),
                "key2", Map.of(Differ.OLD_VALUE_KEY, "value2", Differ.NEW_VALUE_KEY, "value2changed"),
                "key3", Map.of(Differ.OLD_VALUE_KEY, "value3", Differ.NEW_VALUE_KEY, false),
                "key4", Map.of(Differ.OLD_VALUE_KEY, "deletedValue4"),
                "key5", Map.of(Differ.NEW_VALUE_KEY, "newValue5"),
                "key6", Map.of(Differ.OLD_VALUE_KEY, EXAMPLE_INT_1, Differ.NEW_VALUE_KEY, EXAMPLE_INT_2)
        ));

        Map<String, Map<String, Object>> result = diffMaker.make(map1, map2);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateNestedValues() {
        Map<String, Object> map1 = Map.of(
                "key2", Arrays.asList(EXAMPLE_INT_1, EXAMPLE_INT_2)
        );

        Map<String, Object> map2 = Map.of(
                "key1", Map.of("nestedKey", "value", "isNested", true),
                "key2", Arrays.asList(EXAMPLE_INT_3, EXAMPLE_INT_4)
        );

        Map<String, Map<String, Object>> expected = new HashMap<>(Map.of(
                "key1", Map.of(Differ.NEW_VALUE_KEY, Map.of("nestedKey", "value", "isNested", true)),
                "key2", Map.of(Differ.OLD_VALUE_KEY, Arrays.asList(EXAMPLE_INT_1, EXAMPLE_INT_2),
                        Differ.NEW_VALUE_KEY, Arrays.asList(EXAMPLE_INT_3, EXAMPLE_INT_4))
        ));

        Map<String, Map<String, Object>> result = diffMaker.make(map1, map2);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateNullValues() {
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("key1", null);
        map1.put("key2", null);
        map1.put("key3", "value3");

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("key1", EXAMPLE_INT_1);
        map2.put("key2", null);
        map2.put("key3", null);
        map2.put("key4", null);

        Map<String, Map<String, Object>> expected = new LinkedHashMap<>();

        Map<String, Object> value1 = new HashMap<>();
        value1.put(Differ.OLD_VALUE_KEY, null);
        value1.put(Differ.NEW_VALUE_KEY, EXAMPLE_INT_1);

        Map<String, Object> value2 = new HashMap<>();
        value2.put(Differ.OLD_VALUE_KEY, null);
        value2.put(Differ.NEW_VALUE_KEY, null);

        Map<String, Object> value3 = new HashMap<>();
        value3.put(Differ.OLD_VALUE_KEY, "value3");
        value3.put(Differ.NEW_VALUE_KEY, null);

        Map<String, Object> value4 = new HashMap<>();
        value4.put(Differ.NEW_VALUE_KEY, null);

        expected.put("key1", value1);
        expected.put("key2", value2);
        expected.put("key3", value3);
        expected.put("key4", value4);

        Map<String, Map<String, Object>> result = diffMaker.make(map1, map2);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateCheckOrder() {
        Map<String, Object> map1 = Map.of(
                "lastKey1", "value1",
                "middleKey", "value2",
                "firstKey1", "value3"
        );

        Map<String, Object> map2 = Map.of(
                "lastKey1", "value1",
                "middleKey", "value2",
                "firstKey1", "value3"
        );

        Map<String, Map<String, Object>> expected = new LinkedHashMap<>(Map.of(
                "firstKey1", Map.of(Differ.OLD_VALUE_KEY, "value3", Differ.NEW_VALUE_KEY, "value3"),
                "middleKey", Map.of(Differ.OLD_VALUE_KEY, "value2", Differ.NEW_VALUE_KEY, "value2"),
                "lastKey1", Map.of(Differ.OLD_VALUE_KEY, "value1", Differ.NEW_VALUE_KEY, "value1")
        ));

        Map<String, Map<String, Object>> result = diffMaker.make(map1, map2);

        assertThat(result).isEqualTo(expected);
    }
}
