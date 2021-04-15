package com.eric.jetpack.thread.synchronize;

/**
 * @Author: eric
 * @CreateDate: 4/15/21 4:56 PM
 * @Description: java类作用描述
 */
class SynchronizedDemo1 {


    class Runnable1 implements Runnable {
        @Override
        public void run() {
           buy();
        }
    }

    void start() {
        for (int i = 0; i < 6; i++) {
           new Thread(new Runnable1()).start();
        }
    }

   synchronized void buy(){
        try {
            System.out.println(Thread.currentThread().getName() + "  开始买票");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "  结束买票");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo1 demo1 = new SynchronizedDemo1();
        demo1.start();
    }

}
