package hexlet.code;

import java.util.Map;

public interface FileHandlerInterface {
    Map<String, Object> handle(String filepath) throws Exception;
}
