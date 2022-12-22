package threads;

import callbacks.Callback;

import java.util.ArrayList;
import java.util.List;

public abstract class JVAPIRunnable<T> implements Runnable, Cloneable {
    private List<Callback<T>> callbacks;

    protected volatile boolean shouldStop = false;


    public synchronized List<Callback<T>> getCallbacks() {
        return callbacks;
    }
    public synchronized void setCallbacks(List<Callback<T>> callbacks) {
        this.callbacks = callbacks;
    }
    public synchronized void setCallback(Callback<T> onSuccessCallback) {
        this.callbacks = new ArrayList<>();
        this.callbacks.add(onSuccessCallback);
    }
    public synchronized void addCallback(Callback<T> onSuccessCallback) {
        this.callbacks.add(onSuccessCallback);
    }
    public synchronized void addCallbacks(List<Callback<T>> onSuccessCallbacks) {
        this.callbacks.addAll(onSuccessCallbacks);
    }


    public JVAPIRunnable() {
        this.callbacks = new ArrayList<>();
    }


    @Override
    public JVAPIRunnable<T> clone() {
        try {
            JVAPIRunnable<T> clone = (JVAPIRunnable<T>) super.clone();
            clone.setCallbacks(this.callbacks);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Failed cloning JVAPIRunnable");
        }
    }

    @Override
    public void run() { }


    protected synchronized void fireOnSuccessCallbacks(T value) {
        for (Callback<T> callback : callbacks) callback.onSuccess(value);
    }

    protected synchronized void fireOnFailureCallbacks(T value) {
        for (Callback<T> callback : callbacks) callback.onFailure(value);
    }
}