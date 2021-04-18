package com.eric.jetpack.thread.handlerThread;

/**
 * <pre>
 *     author : eric
 *     time   : 2021/04/19
 * </pre>
 */

public class HandlerThreadDemo2 {

    static class CustomThread extends Thread {
        Looper looper = new Looper();
    }

    static class Looper {
        Runnable task;
        boolean quit = false;

        synchronized void setTask(Runnable task) {
            this.task = task;
        }

        synchronized void quit() {
            quit = true;
        }

        public void run() {
            while (!quit) {
                synchronized (this) {
                    if (task != null) {
                        task.run();
                        task = null;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        CustomThread thread = new CustomThread();
        System.out.println("开始测试。。");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.looper.setTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("我打印点东西。。");
            }
        });
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束线程。。");
        thread.looper.quit();
    }

}
