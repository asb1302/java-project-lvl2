package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public final class ContentHandlerImp implements ContentHandler {
    @Override
    public Map<String, Object> handle(String content, String extension) throws Exception {
        ObjectMapper mapper = createObjectMapper(extension);

        TypeReference<Map<String, Object>> type = new TypeReference<>() {
        };

        return mapper.readValue(content, type);
    }

    private ObjectMapper createObjectMapper(String extension) throws Exception {
        switch (extension) {
            case "json" -> {
                return new ObjectMapper();
            }
            case "yaml", "yml" -> {
                return new ObjectMapper(new YAMLFactory());
            }

            default -> throw new Exception("Invalid file extension");
        }
    }
}
