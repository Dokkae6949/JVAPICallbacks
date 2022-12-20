package threads;

import callbacks.Callback;

import java.util.ArrayList;
import java.util.List;

public abstract class JVAPIRunnable implements Runnable {
    private List<Callback> onFinishCallbacks;

    protected volatile boolean shouldStop = false;


    public synchronized List<Callback> getOnFinishCallbacks() {
        return onFinishCallbacks;
    }
    public synchronized JVAPIRunnable setOnFinishCallbacks(List<Callback> onFinishCallbacks) {
        this.onFinishCallbacks = onFinishCallbacks;
        return this;
    }
    public synchronized JVAPIRunnable setOnFinishCallback(Callback onFinishCallback) {
        this.onFinishCallbacks = new ArrayList<>();
        this.onFinishCallbacks.add(onFinishCallback);
        return this;
    }
    public synchronized JVAPIRunnable addOnFinishCallback(Callback onFinishCallback) {
        this.onFinishCallbacks.add(onFinishCallback);
        return this;
    }
    public synchronized JVAPIRunnable addOnFinishCallbacks(List<Callback> onFinishCallbacks) {
        this.onFinishCallbacks.addAll(onFinishCallbacks);
        return this;
    }


    public JVAPIRunnable() {
        this.onFinishCallbacks = new ArrayList<>();
    }
    public JVAPIRunnable(List<Callback> onFinishCallbacks) {
        this.onFinishCallbacks = onFinishCallbacks;
    }
    public JVAPIRunnable(JVAPIRunnable runnable) {
        this.onFinishCallbacks = runnable.getOnFinishCallbacks();
    }


    @Override
    public void run() {
        fireCallbacks();
    }


    protected synchronized void fireCallbacks() {
        for (Callback onFinishCallback : onFinishCallbacks) onFinishCallback.onFire();
    }
}