package tests.callbacks;

import callbacks.Callback;

public class Main {
    public static void main(String[] args) {
        Callback callback = () -> System.out.println("Callback 1");

        callback.onFire();
    }
}