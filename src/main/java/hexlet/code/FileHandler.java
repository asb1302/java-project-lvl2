package hexlet.code;

import java.util.Map;

public interface FileHandler {
    Map<String, Object> handle(String filepath) throws Exception;
}
