package hexlet.code.formatter;

import hexlet.code.Differ;

import java.util.Map;

public final class Stylish extends BaseFormatter implements FormatterInterface {
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

            if (diff.containsKey(Differ.OLD_VALUE_KEY)
                    && diff.containsKey(Differ.NEW_VALUE_KEY)
                    && this.valuesIsEqual(diff.get(Differ.OLD_VALUE_KEY), diff.get(Differ.NEW_VALUE_KEY))) {
                builder.append("   ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(diff.get(Differ.OLD_VALUE_KEY));
            } else if (diff.containsKey(Differ.OLD_VALUE_KEY)
                    && diff.containsKey(Differ.NEW_VALUE_KEY)) {
                builder.append(" - ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(diff.get(Differ.OLD_VALUE_KEY))
                        .append("\n")
                        .append(" + ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(diff.get(Differ.NEW_VALUE_KEY));
            } else if (!diff.containsKey(Differ.OLD_VALUE_KEY)
                    && diff.containsKey(Differ.NEW_VALUE_KEY)) {
                builder.append(" + ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(diff.get(Differ.NEW_VALUE_KEY));
            } else {
                builder.append(" - ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(diff.get(Differ.OLD_VALUE_KEY));
            }
        }
        builder.append("\n}");

        return builder.toString();
    }
}
