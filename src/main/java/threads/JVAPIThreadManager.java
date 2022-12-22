package threads;

public class JVAPIThreadManager {
    private Thread thread;
    private JVAPIRunnable<?> runnableOrigin;
    private JVAPIRunnable<?> runnableActive;


    public JVAPIRunnable<?> getRunnable() {
        return runnableOrigin;
    }
    public void setRunnable(JVAPIRunnable<?> runnableOrigin) {
        this.runnableOrigin = runnableOrigin;
    }


    public JVAPIThreadManager() {}
    public JVAPIThreadManager(JVAPIRunnable<?> runnable) {
        this.runnableOrigin = runnable;
    }


    public void start() {
        if (runnableOrigin == null) return;
        if (thread != null) stop();
        if (thread == null) {
            runnableActive = runnableOrigin.clone();
            runnableActive.shouldStop = false;
            thread = new Thread(runnableActive);
        }

        thread.start();
    }

    public void stop() {
        if (thread == null) return;

        runnableActive.shouldStop = true;
        runnableActive = null;
        thread = null;
    }
}