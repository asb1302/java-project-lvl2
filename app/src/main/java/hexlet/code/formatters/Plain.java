package hexlet.code.formatters;

import hexlet.code.DiffMaker;
import hexlet.code.Node;
import org.apache.commons.lang3.ClassUtils;

import java.util.Map;

public final class Plain extends Formatter {

    @Override
    public String format(Map<String, Node> diff) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Node> entry : diff.entrySet()) {
            Node node = entry.getValue();

            switch (node.getState()) {
                case DiffMaker.CHANGED_STATE:
                    builder.append(
                            printUpdated(
                                    entry.getKey(),
                                    prepareValue(node.getOldValue()),
                                    prepareValue(node.getNewValue())
                            )
                    );
                    break;
                case DiffMaker.NEW_STATE:
                    builder.append(
                            printAdded(
                                    entry.getKey(),
                                    prepareValue(node.getNewValue())
                            )
                    );
                    break;
                case DiffMaker.DELETED_STATE:
                    builder.append(
                            printRemoved(entry.getKey())
                    );
                    break;
                default:
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
