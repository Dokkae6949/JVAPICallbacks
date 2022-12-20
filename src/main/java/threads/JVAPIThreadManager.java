package threads;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JVAPIThreadManager {
    private Thread thread;
    private JVAPIRunnable runnableOrigin;
    private JVAPIRunnable runnableActive;


    public void setRunnable(JVAPIRunnable runnableOrigin) {
        this.runnableOrigin = runnableOrigin;
    }


    public JVAPIThreadManager() {}
    public JVAPIThreadManager(JVAPIRunnable runnable) {
        this.runnableOrigin = runnable;
    }


    public void start() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (runnableOrigin == null) return;
        if (thread != null) stop();
        if (thread == null) {
            runnableActive = cloneRunnable(runnableOrigin);
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


    private JVAPIRunnable cloneRunnable(JVAPIRunnable origin) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = origin.getClass();
        Constructor<?> ctor = clazz.getConstructor();
        Object copy = ctor.newInstance();
        JVAPIRunnable newRunnable = (JVAPIRunnable) copy;
        newRunnable.setOnFinishCallbacks(origin.getOnFinishCallbacks());
        return newRunnable;
    }
}