package com.eric.jetpack.thread.synchronize;

/**
 * <pre>
 *     author : eric
 *     time   : 2021/04/17
 * </pre>
 */

class SynchronizedDemo3 {
    /**
     * 比较这两种情况，synchronized的话 总数是 两个之和，不加的话会小一点
     */
    private int i;

    //    private synchronized void count() {
//        i++;
//    }
    private void count() {
        i++;
    }


    private class Runnable1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000_000; i++) {
                count();
            }
            System.out.println("form thread1 :" + i);
        }
    }

    private class Runnable2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000_000; i++) {
                count();
            }
            System.out.println("form thread2 :" + i);
        }
    }


    void start() {
        new Thread(new Runnable1()).start();
        new Thread(new Runnable2()).start();
    }


    public static void main(String[] args) {
        SynchronizedDemo3 demo3 = new SynchronizedDemo3();
        demo3.start();
    }
}
