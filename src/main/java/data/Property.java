package data;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

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

    public void addListener(ChangeListener<T> listener) {
        this.listeners.add(new WeakReference<>(listener));
    }

    public void removeListener(ChangeListener<T> listener) {
        this.listeners.removeIf(reference -> (reference.refersTo(null) || reference.refersTo(listener)));
    }

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