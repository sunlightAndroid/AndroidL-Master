package com.eric.jetpack.thread.question;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author : eric
 *     time   : 2021/04/21
 * </pre>
 */

class Question1 {
//    ReentrantLock lock = new ReentrantLock(false); // 非公平锁
    ReentrantLock lock = new ReentrantLock(true); // 公平锁
    void methodC() {
        try {
            lock.lock();
            methodA();
            lock.unlock();

            lock.lock();
            methodB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void methodA() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("A 方法  :" + Thread.currentThread().getName());
    }

    void methodB() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("B 方法  :" + Thread.currentThread().getName());
    }

    class RunImp implements Runnable {
        @Override
        public void run() {
            methodC();
        }
    }
    public void start() {
        for (int i = 0; i < 10; i++) {
            new Thread(new RunImp()).start();
        }
    }

    public static void main(String[] args) {
        Question1 test = new Question1();
        test.start();
    }
}
