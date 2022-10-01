package hexlet.code;

import java.util.Map;

public interface ContentHandler {
    Map<String, Object> handle(String content, String extension) throws Exception;
}
