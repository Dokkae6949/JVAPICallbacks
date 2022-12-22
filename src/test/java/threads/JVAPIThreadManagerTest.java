package threads;

import org.junit.jupiter.api.Test;
import threads.runnable.TestRunnableWithConstructor;

import static org.junit.jupiter.api.Assertions.*;

class JVAPIThreadManagerTest {
    @Test
    public void noRunnableTest() {
        assertDoesNotThrow(() -> {
            JVAPIThreadManager tManager = new JVAPIThreadManager();

            tManager.start();
            tManager.stop();
        });
    }

    @Test
    public void stopBeforeStartWithoutRunnableTest() {
        assertDoesNotThrow(() -> {
            JVAPIThreadManager tManager = new JVAPIThreadManager();

            tManager.stop();
            tManager.start();
        });
    }

    @Test
    public void simpleRunnableTest() {
        assertDoesNotThrow(() -> {
            JVAPIThreadManager tManager = new JVAPIThreadManager();
            tManager.setRunnable(new TestRunnableWithConstructor(1707));

            tManager.stop();
            tManager.start();
        });
    }
}