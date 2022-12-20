package tests.threads;

import callbacks.Callback;
import threads.JVAPIThreadManager;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JVAPIThreadManager tManager = new JVAPIThreadManager();


        tManager.stop();
        tManager.start();

        tManager.setRunnable(new TestRunnable().setOnFinishCallback(new TestCallback()));

        for (int i = 0; i < 2; i++) {
            System.out.println("Starting Thread: " + i);
            tManager.start();

            System.out.println("Main Sleeping: " + i);
            Thread.sleep(2000);

            System.out.println("Stopping Thread: " + i);
            tManager.stop();

            System.out.println("=======================");
        }

        tManager.setRunnable(new TestRunnable().setOnFinishCallback(() -> System.out.println("Kurisu Makise <3")));

        for (int i = 0; i < 2; i++) {
            System.out.println("Starting Thread: " + i);
            tManager.start();

            System.out.println("Main Sleeping: " + i);
            Thread.sleep(2000);

            System.out.println("Stopping Thread: " + i);
            tManager.stop();

            System.out.println("=======================");
        }
    }
}
