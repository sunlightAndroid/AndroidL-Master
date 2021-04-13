package com.eric.jetpack.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatchDemo demo = new CountDownLatchDemo();
        demo.startCar(15);
    }


    public void startCar(int size)  {
        CountDownLatch countDownLatch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "准备好了");
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("都已经准备好了，开始发车   " + Thread.currentThread().getName());
    }
}
