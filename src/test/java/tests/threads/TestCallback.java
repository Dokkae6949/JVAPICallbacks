package tests.threads;

import callbacks.Callback;

public class TestCallback implements Callback {
    @Override
    public void onFire() {
        System.out.println("Callback here !!!");
    }
}
