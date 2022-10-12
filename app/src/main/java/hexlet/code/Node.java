package hexlet.code;

public final class Node {
    private String state;

    private Object oldValue;

    private Object newValue;

    /**
     * Get node state.
     *
     * @return node state.
     */
    public String getState() {
        return state;
    }

    /**
     * Set node state.
     *
     * @param s node state.
     */
    public void setState(String s) {
        this.state = s;
    }

    /**
     * Get node old value.
     *
     * @return node old value.
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Set node old value.
     *
     * @param k node old value.
     */
    public void setOldValue(Object k) {
        this.oldValue = k;
    }

    /**
     * Get node new value.
     *
     * @return node new value.
     */
    public Object getNewValue() {
        return newValue;
    }

    /**
     * Set node new value.
     *
     * @param k node new value.
     */
    public void setNewValue(Object k) {
        this.newValue = k;
    }
}
