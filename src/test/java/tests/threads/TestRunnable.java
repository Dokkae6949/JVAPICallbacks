package tests.threads;

import threads.JVAPIRunnable;

public class TestRunnable extends JVAPIRunnable {
    @Override
    public void run() {
        int i = 0;
        while (!shouldStop) {
            System.out.println(shouldStop + " | " + i);
            i++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        fireCallbacks();
    }
}
