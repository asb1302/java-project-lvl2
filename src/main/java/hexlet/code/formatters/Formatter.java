package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.Map;

public abstract class Formatter {
    abstract public String format(Map<String, Map<String, Object>> diffMap) throws Exception;

    /**
     * Define, that property was not changed.
     *
     * @param diff map with old and new value
     * @return result of the comparison
     */
    protected boolean isNotChangedProperty(Map<String, Object> diff) {
        return diff.containsKey(Differ.OLD_VALUE_KEY)
                && diff.containsKey(Differ.NEW_VALUE_KEY)
                && this.valuesIsEqual(diff.get(Differ.OLD_VALUE_KEY), diff.get(Differ.NEW_VALUE_KEY));
    }

    /**
     * Define, that property was not updated.
     *
     * @param diff map with old and new value
     * @return result of the comparison
     */
    protected boolean isUpdatedProperty(Map<String, Object> diff) {
        return diff.containsKey(Differ.OLD_VALUE_KEY)
                && diff.containsKey(Differ.NEW_VALUE_KEY);
    }

    /**
     * Define, that property was not added.
     *
     * @param diff map with old and new value
     * @return result of the comparison
     */
    protected boolean isAddedProperty(Map<String, Object> diff) {
        return !diff.containsKey(Differ.OLD_VALUE_KEY)
                && diff.containsKey(Differ.NEW_VALUE_KEY);
    }

    /**
     * Define, that property was removed.
     *
     * @param diff map with old and new value
     * @return result of the comparison
     */
    protected boolean isRemovedProperty(Map<String, Object> diff) {
        return diff.containsKey(Differ.OLD_VALUE_KEY)
                && !diff.containsKey(Differ.NEW_VALUE_KEY);
    }

    private boolean valuesIsEqual(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        }

        if (value1 != null && value2 != null) {
            return value1.equals(value2);
        }

        return false;
    }
}
