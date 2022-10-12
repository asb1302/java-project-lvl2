package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffMaker;
import hexlet.code.Node;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Json extends Formatter {
    private static final String OLD_VALUE_KEY = "oldValue";
    private static final String NEW_VALUE_KEY = "newValue";

    @Override
    public String format(Map<String, Node> diff) throws Exception {
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();

        for (Map.Entry<String, Node> entry : diff.entrySet()) {
            Node node = entry.getValue();

            Map<String, Object> value = new LinkedHashMap<>();

            if (!node.getState().equals(DiffMaker.NEW_STATE)) {
                value.put(OLD_VALUE_KEY, node.getOldValue());
            }

            if (!node.getState().equals(DiffMaker.DELETED_STATE)) {
                value.put(NEW_VALUE_KEY, node.getNewValue());
            }

            result.put(entry.getKey(), value);
        }

        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
