package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class DiffMaker implements DiffMakerInterface {
    @Override
    public Map<String, Map<String, Object>> make(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        return keys.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        key ->
                                (!map1.containsKey(key) ? createDiffMap(Differ.NEW_VALUE_KEY, map2.get(key))
                                        :
                                        !map2.containsKey(key) ? createDiffMap(Differ.OLD_VALUE_KEY, map1.get(key))
                                                :
                                                createDiffMap(Differ.OLD_VALUE_KEY, map1.get(key),
                                                        Differ.NEW_VALUE_KEY, map2.get(key))
                                ),
                        (k1, k2) -> k1, LinkedHashMap::new)
                );
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
