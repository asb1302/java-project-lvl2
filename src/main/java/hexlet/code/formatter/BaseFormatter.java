package hexlet.code.formatter;

public abstract class BaseFormatter {
    /**
     *
     * Compare values.
     *
     * @param value1
     * @param value2
     *
     * @return result of the comparison
     */
    protected boolean valuesIsEqual(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        }

        if (value1 != null && value2 != null) {
            return value1.equals(value2);
        }

        return false;
    }
}
