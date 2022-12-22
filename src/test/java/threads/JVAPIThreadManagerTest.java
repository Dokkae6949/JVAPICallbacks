package threads;

import org.junit.jupiter.api.Test;
import threads.runnable.TestRunnable;

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
            tManager.setRunnable(new TestRunnable());

            tManager.stop();
            tManager.start();
        });
    }
}