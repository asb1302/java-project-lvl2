package hexlet.code.domain;

public final class Diff {
    private final Object oldValue;
    private final Object newValue;

    public Diff(Object oldV, Object newV) {
        this.oldValue = oldV;
        this.newValue = newV;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
