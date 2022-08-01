package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.domain.Diff;

import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Map<String, Object>> type = new TypeReference<>() {
        };

        Map<String, Object> mapFile1 = mapper.readValue(Paths.get(filePath1).toFile(), type);
        Map<String, Object> mapFile2 = mapper.readValue(Paths.get(filePath2).toFile(), type);

        Set<String> keys = new TreeSet<>(mapFile1.keySet());
        keys.addAll(mapFile2.keySet());

        LinkedHashMap<String, Diff> result = keys.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        key ->
                                (!mapFile1.containsKey(key) ? new Diff(null, mapFile2.get(key))
                                        :
                                        !mapFile2.containsKey(key) ? new Diff(mapFile1.get(key), null)
                                                :
                                                new Diff(mapFile1.get(key), mapFile2.get(key))),
                        (k1, k2) -> k1, LinkedHashMap::new)
                );


        boolean first = true;
        StringBuilder builder = new StringBuilder();

        builder.append("{\n");
        for (Map.Entry<String, Diff> entry : result.entrySet()) {
            if (first) {
                first = false;
            } else {
                builder.append("\n");
            }

            Diff diff = entry.getValue();

            if (diff.getOldValue() != null && diff.getOldValue().equals(diff.getNewValue())) {
                builder.append("   ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue().getOldValue());
            } else if (diff.getOldValue() != null && diff.getNewValue() != null) {
                builder.append(" - ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue().getOldValue())
                        .append("\n")
                        .append(" + ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue().getNewValue());
            } else if (diff.getOldValue() == null && diff.getNewValue() != null) {
                builder.append(" + ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue().getNewValue());
            } else {
                builder.append(" - ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue().getOldValue());
            }


        }
        builder.append("\n}");

        return builder.toString();
    }
}
