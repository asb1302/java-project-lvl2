package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.Map;

public final class Stylish extends BaseFormatter implements Formatter {
    @Override
    public String format(Map<String, Map<String, Object>> diffMap) {
        boolean first = true;

        StringBuilder builder = new StringBuilder();

        builder.append("{\n");
        for (Map.Entry<String, Map<String, Object>> entry : diffMap.entrySet()) {
            if (first) {
                first = false;
            } else {
                builder.append("\n");
            }

            Map<String, Object> diff = entry.getValue();

            if (isNotChangedProperty(diff)) {
                builder
                        .append(printNotChanged(entry.getKey(), diff.get(Differ.OLD_VALUE_KEY)));
            } else if (isUpdatedProperty(diff)) {
                builder
                        .append(printMinus(entry.getKey(), diff.get(Differ.OLD_VALUE_KEY)))
                        .append("\n")
                        .append(printPlus(entry.getKey(), diff.get(Differ.NEW_VALUE_KEY)));
            } else if (isAddedProperty(diff)) {
                builder
                        .append(printPlus(entry.getKey(), diff.get(Differ.NEW_VALUE_KEY)));
            } else if (isRemovedProperty(diff)) {
                builder
                        .append(printMinus(entry.getKey(), diff.get(Differ.OLD_VALUE_KEY)));
            }
        }

        builder.append("\n}");

        return builder.toString();
    }

    private String printNotChanged(String key, Object value) {
        return "    "
                +
                key
                +
                ": "
                +
                value;
    }

    private String printPlus(String key, Object value) {
        return "  + "
                +
                key
                +
                ": "
                +
                value;
    }

    private String printMinus(String key, Object value) {
        return "  - "
                +
                key
                +
                ": "
                +
                value;
    }
}
