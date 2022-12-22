package threads;

import callbacks.Callback;

import java.util.ArrayList;
import java.util.List;

public abstract class JVAPIRunnable<T> implements Runnable, Cloneable {
    private List<Callback<T>> onSuccessCallbacks;
    private List<Callback<T>> onFailureCallbacks;

    protected volatile boolean shouldStop = false;


    public synchronized List<Callback<T>> getOnSuccessCallbacks() {
        return onSuccessCallbacks;
    }
    public synchronized JVAPIRunnable<T> setOnSuccessCallbacks(List<Callback<T>> onSuccessCallbacks) {
        this.onSuccessCallbacks = onSuccessCallbacks;
        return this;
    }
    public synchronized JVAPIRunnable<T> setOnSuccessCallback(Callback<T> onSuccessCallback) {
        this.onSuccessCallbacks = new ArrayList<>();
        this.onSuccessCallbacks.add(onSuccessCallback);
        return this;
    }
    public synchronized JVAPIRunnable<T> addOnSuccessCallback(Callback<T> onSuccessCallback) {
        this.onSuccessCallbacks.add(onSuccessCallback);
        return this;
    }
    public synchronized JVAPIRunnable<T> addOnSuccessCallbacks(List<Callback<T>> onSuccessCallbacks) {
        this.onSuccessCallbacks.addAll(onSuccessCallbacks);
        return this;
    }

    public synchronized List<Callback<T>> getOnFailureCallbacks() {
        return onFailureCallbacks;
    }
    public synchronized JVAPIRunnable<T> setOnFailureCallbacks(List<Callback<T>> onFailureCallbacks) {
        this.onFailureCallbacks = onFailureCallbacks;
        return this;
    }
    public synchronized JVAPIRunnable<T> setOnFailureCallback(Callback<T> onFailureCallback) {
        this.onFailureCallbacks = new ArrayList<>();
        this.onFailureCallbacks.add(onFailureCallback);
        return this;
    }
    public synchronized JVAPIRunnable<T> addOnFailureCallback(Callback<T> onFailureCallback) {
        this.onFailureCallbacks.add(onFailureCallback);
        return this;
    }
    public synchronized JVAPIRunnable<T> addOnFailureCallbacks(List<Callback<T>> onFailureCallbacks) {
        this.onFailureCallbacks.addAll(onFailureCallbacks);
        return this;
    }


    public JVAPIRunnable() {
        this.onSuccessCallbacks = new ArrayList<>();
        this.onFailureCallbacks = new ArrayList<>();
    }


    @Override
    public JVAPIRunnable<T> clone() {
        try {
            JVAPIRunnable<T> clone = (JVAPIRunnable<T>) super.clone();
            clone.setOnSuccessCallbacks(this.onSuccessCallbacks);
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
        for (Callback<T> callback : onSuccessCallbacks) callback.onSuccess(value);
    }

    protected synchronized void fireOnFailureCallbacks(T value) {
        for (Callback<T> callback : onFailureCallbacks) callback.onFailure(value);
    }
}