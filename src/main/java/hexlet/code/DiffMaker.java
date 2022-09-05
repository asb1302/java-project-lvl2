package hexlet.code;

import java.util.Map;

public interface DiffMaker {
    Map<String, Map<String, Object>> make(Map<String, Object> map1, Map<String, Object> map2);
}
