package com.eric.jetpack.thread.synchronize;

/**
 * <pre>
 *     author : eric
 *     time   : 2021/04/17
 * </pre>
 */

class SynchronizedDemo4 {

    private Object monitor = new Object();

    private synchronized void methodA() {
        System.out.println("methodA start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("methodA end");
    }

    private synchronized void methodB() {
        System.out.println("methodB start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("methodB end");
    }

    private void methodC() {
        System.out.println("methodC start");
        synchronized (monitor) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("methodC end");
        }
        System.out.println("methodC 代码块之外的");
    }

    private void methodD() {
        System.out.println("methodD 开始执行了");
    }


    public static void main(String[] args) {
        SynchronizedDemo4 demo4 = new SynchronizedDemo4();
//        new Thread(() -> demo4.methodA()).start();
//        new Thread(() -> demo4.methodB()).start();
//        // 说明了methodA methodB 都是同一个monitor监视，两个方法都是串行的
//        System.out.println("====================情况一 结束==================");
//
//        new Thread(() -> demo4.methodB()).start();
//        new Thread(() -> demo4.methodC()).start();
//        // 说明了methodB methodC 都是不同的monitor监视，两个方法都是并行的
//        System.out.println("====================情况二 结束==================");

        new Thread(() -> demo4.methodC()).start();
        new Thread(() -> demo4.methodD()).start();
        System.out.println("====================情况三 结束==================");


        // 线程打断的方法
        Thread thread = new Thread();
        thread.stop();
        thread.interrupt();
        boolean interrupted = thread.isInterrupted();


    }
}
