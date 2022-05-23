package data;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * A Property is a field that can be changed and inform listeners of this change
 * @param <T> The type of the Property
 * @see  ChangeListener
 */
public class Property <T> {

    private T data;
    private final List<WeakReference<ChangeListener<T>>> listeners = new LinkedList<>();

    public Property() {
    }

    public Property(T data) {
        this.data = data;
    }

    public T get() {
        return data;
    }

    /**
     * Sets the current value of the porperty. All {@link ChangeListener} are informed of this change
     * @param data The new Value of the Property
     */
    public synchronized void set(T data) {
        //notify about change
        for (WeakReference<ChangeListener<T>> reference : this.listeners) {
            if (reference.get() != null) {
                ChangeListener<T> listener = reference.get();
                listener.changed(this, this.data, data);
            } else {
                this.listeners.remove(reference);
            }
        }
        this.data = data;
    }

    /**
     * Adds a listener
     * @param listener The listener to be added
     * @see ChangeListener
     */
    public void addListener(ChangeListener<T> listener) {
        this.listeners.add(new WeakReference<>(listener));
    }

    /**
     * Removes a listener
     * @param listener The listener to be removed
     * @see ChangeListener
     */
    public void removeListener(ChangeListener<T> listener) {
        this.listeners.removeIf(reference -> (reference.refersTo(null) || reference.refersTo(listener)));
    }

    /**
     * Returns a list of all currently registered listeners
     * @return The list of listeners
     * @see ChangeListener
     */
    public List<ChangeListener<T>> getListeners(){
        List<ChangeListener<T>> listeners = new LinkedList<>();

        for (WeakReference<ChangeListener<T>> reference : this.listeners){
            if (reference.get() == null){
                this.listeners.remove(reference);
            }else {
                listeners.add(reference.get());
            }
        }
        return listeners;
    }
}