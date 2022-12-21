package threads;

import org.junit.jupiter.api.Test;

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
}