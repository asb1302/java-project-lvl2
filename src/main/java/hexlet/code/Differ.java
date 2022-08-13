package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Differ implements DifferInterface {
    public static final String OLD_VALUE_KEY = "oldValue";
    public static final String NEW_VALUE_KEY = "newValue";

    @Override
    public String generate(Map<String, Object> map1, Map<String, Object> map2, String format) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        Map<String, Map<String, Object>> diff = keys.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        key ->
                                (!map1.containsKey(key) ? createDiffMap(NEW_VALUE_KEY, map2.get(key))
                                        :
                                        !map2.containsKey(key) ? createDiffMap(OLD_VALUE_KEY, map1.get(key))
                                                :
                                                createDiffMap(OLD_VALUE_KEY, map1.get(key),
                                                        NEW_VALUE_KEY, map2.get(key))
                                ),
                        (k1, k2) -> k1, LinkedHashMap::new)
                );

        return Formatter.getFormatter(format).format(diff);
    }

    private Map<String, Object> createDiffMap(String key, Object value) {
        Map<String, Object> diffMap = new LinkedHashMap<>();
        diffMap.put(key, value);

        return diffMap;
    }

    private Map<String, Object> createDiffMap(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> diffMap = new LinkedHashMap<>();
        diffMap.put(key1, value1);
        diffMap.put(key2, value2);

        return diffMap;
    }
}
