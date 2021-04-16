package com.eric.jetpack.thread.synchronize;

/**
 * <pre>
 *     author : eric
 *     time   : 2021/04/17
 * </pre>
 */

class SynchronizedDemo2 {

    private int a, b;

    private void print(int newValue) {
        a = newValue;
        b = newValue;

        if (a != b) {
            System.out.println(a + " ---- " + b);
        }
    }


    private class Runnable1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000_000_000; i++) {
                print(i);
            }
        }
    }

    private class Runnable2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000_000_000; i++) {
                print(i);
            }
        }
    }


    void start() {
        new Thread(new Runnable1()).start();
        new Thread(new Runnable2()).start();
    }


    public static void main(String[] args) {
        SynchronizedDemo2 demo2 = new SynchronizedDemo2();
        demo2.start();
    }
}
