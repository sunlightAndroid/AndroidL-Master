package com.eric.jetpack.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: eric
 * @CreateDate: 4/20/21 6:52 PM
 * @Description: java类作用描述
 */
class AtomicDemo {

    // 说明被volatile修饰的共享变量不能保证非原子性操作的线程安全，
    // 除非只有赋值操作，比如a =100
    volatile int a;
    AtomicInteger b = new AtomicInteger();

    class Runnable1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                add();
            }
        }
    }


    private void add() {
        a++;
        b.getAndIncrement();
    }

    private void test() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable1());
        Thread thread2 = new Thread(new Runnable1());
        thread1.start();
        thread2.start();

        // 插个队， thread1， thread2 都执行完了再去执行主线程
        thread1.join();
        thread2.join();
    }

    public static void main(String[] args) {
        AtomicDemo demo = new AtomicDemo();
        try {
            demo.test();
            System.out.println(demo.a + " " + demo.b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----------");
    }
}
