package com.eric.jetpack.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Author: eric
 * @CreateDate: 4/15/21 6:01 PM
 * @Description: 线程池
 */
class ThreadPoolDemo1 {
    ExecutorService executorService;

    void init() {
        executorService = Executors.newCachedThreadPool();

    }

    class Runnable1 implements Runnable{

        @Override
        public void run() {
           sleep();
        }
    }

    private synchronized void sleep(){
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + " 准备睡觉了");
            Thread.sleep(2000);
            System.out.println(name + " 起床了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void test(){
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable1());
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        ThreadPoolDemo1 demo1 = new ThreadPoolDemo1();
        demo1.init();
        demo1.test();
    }


}
