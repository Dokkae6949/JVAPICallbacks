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
    public synchronized JVAPIRunnable<T> setCallbacks(List<Callback<T>> callbacks) {
        this.callbacks = callbacks;
        return this;
    }
    public synchronized JVAPIRunnable<T> setCallback(Callback<T> onSuccessCallback) {
        this.callbacks = new ArrayList<>();
        this.callbacks.add(onSuccessCallback);
        return this;
    }
    public synchronized JVAPIRunnable<T> addCallback(Callback<T> onSuccessCallback) {
        this.callbacks.add(onSuccessCallback);
        return this;
    }
    public synchronized JVAPIRunnable<T> addCallbacks(List<Callback<T>> onSuccessCallbacks) {
        this.callbacks.addAll(onSuccessCallbacks);
        return this;
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
    public void run() {
        fireOnSuccessCallbacks(null);
    }


    protected synchronized void fireOnSuccessCallbacks(T value) {
        for (Callback<T> callback : callbacks) callback.onSuccess(value);
    }

    protected synchronized void fireOnFailureCallbacks(T value) {
        for (Callback<T> callback : callbacks) callback.onFailure(value);
    }
}