package threads;

import callbacks.Callback;

import java.util.ArrayList;
import java.util.List;

public abstract class JVAPIRunnable implements Runnable, Cloneable {
    private List<Callback> onSuccessCallbacks;
    private List<Callback> onFailureCallbacks;

    protected volatile boolean shouldStop = false;


    public synchronized List<Callback> getOnSuccessCallbacks() {
        return onSuccessCallbacks;
    }
    public synchronized JVAPIRunnable setOnSuccessCallbacks(List<Callback> onSuccessCallbacks) {
        this.onSuccessCallbacks = onSuccessCallbacks;
        return this;
    }
    public synchronized JVAPIRunnable setOnSuccessCallback(Callback onSuccessCallback) {
        this.onSuccessCallbacks = new ArrayList<>();
        this.onSuccessCallbacks.add(onSuccessCallback);
        return this;
    }
    public synchronized JVAPIRunnable addOnSuccessCallback(Callback onSuccessCallback) {
        this.onSuccessCallbacks.add(onSuccessCallback);
        return this;
    }
    public synchronized JVAPIRunnable addOnSuccessCallbacks(List<Callback> onSuccessCallbacks) {
        this.onSuccessCallbacks.addAll(onSuccessCallbacks);
        return this;
    }

    public synchronized List<Callback> getOnFailureCallbacks() {
        return onFailureCallbacks;
    }
    public synchronized JVAPIRunnable setOnFailureCallbacks(List<Callback> onFailureCallbacks) {
        this.onFailureCallbacks = onFailureCallbacks;
        return this;
    }
    public synchronized JVAPIRunnable setOnFailureCallback(Callback onFailureCallback) {
        this.onFailureCallbacks = new ArrayList<>();
        this.onFailureCallbacks.add(onFailureCallback);
        return this;
    }
    public synchronized JVAPIRunnable addOnFailureCallback(Callback onFailureCallback) {
        this.onFailureCallbacks.add(onFailureCallback);
        return this;
    }
    public synchronized JVAPIRunnable addOnFailureCallbacks(List<Callback> onFailureCallbacks) {
        this.onFailureCallbacks.addAll(onFailureCallbacks);
        return this;
    }


    public JVAPIRunnable() {
        this.onSuccessCallbacks = new ArrayList<>();
        this.onFailureCallbacks = new ArrayList<>();
    }


    @Override
    public JVAPIRunnable clone() {
        try {
            JVAPIRunnable clone = (JVAPIRunnable) super.clone();
            clone.setOnSuccessCallbacks(this.onSuccessCallbacks);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void run() {
        fireOnSuccessCallbacks();
    }


    protected synchronized void fireOnSuccessCallbacks() {
        for (Callback callback : onSuccessCallbacks) callback.onSuccess();
    }

    protected synchronized void fireOnFailureCallbacks() {
        for (Callback callback : onFailureCallbacks) callback.onFailure();
    }
}