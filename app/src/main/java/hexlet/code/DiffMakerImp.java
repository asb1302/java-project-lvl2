package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class DiffMakerImp implements DiffMaker {
    @Override
    public Map<String, Node> make(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        return keys.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        key ->
                                (!map1.containsKey(key) ? createNode(NEW_STATE, map2.get(key))
                                        :
                                        !map2.containsKey(key) ? createNode(DELETED_STATE, map1.get(key))
                                                :
                                                createNode(map1.get(key), map2.get(key))
                                ),
                        (k1, k2) -> k1, LinkedHashMap::new)
                );
    }

    private Node createNode(String state, Object value) {
        Node node = new Node();

        node.setState(state);

        if (state.equals(DELETED_STATE)) {
            node.setOldValue(value);
        }

        if (state.equals(NEW_STATE)) {
            node.setNewValue(value);
        }

        return node;
    }

    private Node createNode(Object oldValue, Object newValue) {
        Node node = new Node();

        String state = CHANGED_STATE;
        if (isNotChangedProperty(oldValue, newValue)) {
            state = NOT_CHANGED_STATE;
        }

        node.setState(state);

        node.setOldValue(oldValue);
        node.setNewValue(newValue);

        return node;
    }

    private boolean isNotChangedProperty(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        }

        if (value1 != null && value2 != null) {
            return value1.equals(value2);
        }

        return false;
    }
}
