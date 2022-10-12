package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.Map;

public abstract class Formatter {
    public abstract String format(Map<String, Node> diff) throws Exception;
}
