package com.eric.jetpack.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: eric
 * @CreateDate: 4/15/21 4:56 PM
 * @Description: java类作用描述
 */
class ReentrantlockDemo1 {


    class Runnable1 implements Runnable {
        @Override
        public void run() {
           buy();
        }
    }
    ReentrantLock lock = new ReentrantLock(false); // 非公平锁 相当于synchronized
    void buy(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "  开始买票");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "  结束买票");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void start() {
        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable1()).start();
        }
    }

    public static void main(String[] args) {
        ReentrantlockDemo1 demo1 = new ReentrantlockDemo1();
        demo1.start();
    }

}
