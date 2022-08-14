package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class Json extends BaseFormatter implements FormatterInterface {
    @Override
    public String format(Map<String, Map<String, Object>> diffMap) throws Exception {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(diffMap);
    }
}
