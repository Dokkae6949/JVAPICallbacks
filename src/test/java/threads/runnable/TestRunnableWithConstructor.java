package threads.runnable;

import threads.JVAPIRunnable;

public class TestRunnableWithConstructor extends JVAPIRunnable {
    private int number;

    public TestRunnableWithConstructor(int number) {
        this.number = number;
    }
}
