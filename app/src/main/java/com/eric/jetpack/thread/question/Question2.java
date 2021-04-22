package com.eric.jetpack.thread.question;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author : eric
 *     time   : 2021/04/21
 * </pre>
 */

class Question2 {
    ReentrantLock lock = new ReentrantLock(false);
    Condition conditionB = lock.newCondition();
    private AtomicInteger atomicInteger = new AtomicInteger();

    void methodA() {
        try {
            lock.lock();
            int count = atomicInteger.getAndIncrement();
            if (count == 9) {
                conditionB.signalAll();
            }
            System.out.println("A 方法  :" + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }

    void methodB() {
        try {
            lock.lock();
            conditionB.await();
            System.out.println("B 方法  :" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException  :" + e.getMessage());
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void methodC() {
        try {
            lock.lock();
            methodD();
            lock.unlock();

            lock.lock();
            methodE();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void methodD() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("D 方法  :" + Thread.currentThread().getName());
    }

    void methodE() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("E 方法  :" + Thread.currentThread().getName());
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
        Question2 test = new Question2();
        test.start();
    }

}
