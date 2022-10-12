package hexlet.code.formatters;

import hexlet.code.DiffMaker;
import hexlet.code.Node;

import java.util.Map;

public final class Stylish extends Formatter {
    @Override
    public String format(Map<String, Node> diff) {
        boolean first = true;

        StringBuilder builder = new StringBuilder();

        builder.append("{\n");
        for (Map.Entry<String, Node> entry : diff.entrySet()) {
            if (first) {
                first = false;
            } else {
                builder.append("\n");
            }

            Node node = entry.getValue();

            switch (node.getState()) {
                case DiffMaker.NOT_CHANGED_STATE -> builder
                        .append(printNotChanged(entry.getKey(), node.getOldValue()));
                case DiffMaker.CHANGED_STATE -> builder
                        .append(printMinus(entry.getKey(), node.getOldValue()))
                        .append("\n")
                        .append(printPlus(entry.getKey(), node.getNewValue()));
                case DiffMaker.NEW_STATE -> builder
                        .append(printPlus(entry.getKey(), node.getNewValue()));
                case DiffMaker.DELETED_STATE -> builder
                        .append(printMinus(entry.getKey(), node.getOldValue()));
                default -> {
                    continue;
                }
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
