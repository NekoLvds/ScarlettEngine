package data;

/**
 * Functional interface for receiving {@link Property} change events
 * @param <T> The type of the Property
 */
public interface ChangeListener<T> {
    void changed(Property<T> property, T oldValue, T newValue);
}
