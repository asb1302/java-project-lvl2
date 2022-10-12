package hexlet.code;

import java.util.Map;

public interface DiffMaker {

    String NOT_CHANGED_STATE = "not-changed";
    String NEW_STATE = "new";
    String CHANGED_STATE = "changed";
    String DELETED_STATE = "deleted";

    Map<String, Node> make(Map<String, Object> map1, Map<String, Object> map2);
}
