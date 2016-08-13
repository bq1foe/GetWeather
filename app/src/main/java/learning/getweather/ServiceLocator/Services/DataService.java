package learning.getweather.ServiceLocator.Services;

public abstract class DataService<T> {
    private T value;

    public T getValue() {
        if (value != null) {
            return value;
        } else {
            value = initValue();
        }
        return value;
    }

    public void setValue(final T value) {
        saveValue(value);
        this.value = value;
    }

    protected abstract T initValue();

    protected abstract void saveValue(T value);
}
