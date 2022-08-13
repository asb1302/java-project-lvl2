package hexlet.code.formatters;

import java.util.Map;

public interface FormatterInterface {
    String format(Map<String, Map<String, Object>> diffMap);
}
