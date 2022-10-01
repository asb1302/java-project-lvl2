package hexlet.code.formatters;

import hexlet.code.Differ;
import org.apache.commons.lang3.ClassUtils;

import java.util.Map;

public final class Plain extends Formatter {

    @Override
    public String format(Map<String, Map<String, Object>> diffMap) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Map<String, Object>> entry : diffMap.entrySet()) {
            Map<String, Object> diff = entry.getValue();

            if (!isNotChangedProperty(diff) && isUpdatedProperty(diff)) {
                builder.append(
                        printUpdated(
                                entry.getKey(),
                                prepareValue(diff.get(Differ.OLD_VALUE_KEY)),
                                prepareValue(diff.get(Differ.NEW_VALUE_KEY))
                        )
                );
            } else if (isAddedProperty(diff)) {
                builder.append(
                        printAdded(
                                entry.getKey(),
                                prepareValue(diff.get(Differ.NEW_VALUE_KEY))
                        )
                );
            } else if (isRemovedProperty(diff)) {
                builder.append(
                        printRemoved(entry.getKey())
                );
            } else {
                continue;
            }

            builder.append("\n");
        }

        return builder.toString().trim();
    }

    private Object prepareValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return String.format("'%s'", value);
        }

        if (ClassUtils.isPrimitiveWrapper(value.getClass())) {
            return value;
        }

        return "[complex value]";
    }

    private String printUpdated(String key, Object oldValue, Object newValue) {
        return String.format("Property '%s' was updated. From %s to %s", key, oldValue, newValue);
    }

    private String printAdded(String key, Object value) {
        return String.format("Property '%s' was added with value: %s", key, value);
    }

    private String printRemoved(String key) {
        return String.format("Property '%s' was removed", key);
    }
}
