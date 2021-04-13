package com.eric.jetpack.thread;


import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreDemo demo = new SemaphoreDemo();
        demo.play(10,3);
    }

    /**
     *
     * @param allPersons 总共多少人
     * @param limitInHouse 院子里同时只能有多少人
     */
    public void play(int allPersons, int limitInHouse){
        Semaphore semaphore = new Semaphore(limitInHouse,true);
        for (int i = 0; i < allPersons; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        semaphore.acquire();
                        System.out.println(name + "获取到了许可证，进去玩了");
                        // 模拟玩了多少时间
                        Thread.sleep(5000);

                        semaphore.release();
                        System.out.println(name +"归还了许可证");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
