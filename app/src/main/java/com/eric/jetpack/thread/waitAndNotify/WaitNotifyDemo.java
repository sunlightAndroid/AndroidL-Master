package com.eric.jetpack.thread.waitAndNotify;

/**
 * @Author: eric
 * @CreateDate: 4/13/21 8:23 PM
 * @Description: java类作用描述
 */
class WaitNotifyDemo {

    private String name = "this is null";

    private synchronized void updateName(String name){
        this.name = name;
        notifyAll();
    }

    private synchronized void printName(){
        while (name.equals("this is null")){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.name); 
    }


    public static void main(String[] args) {
        WaitNotifyDemo demo = new WaitNotifyDemo();

        new Thread(() -> {
            try {
                Thread.sleep(500);
                demo.printName();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        new Thread(() -> {
            try {
                Thread.sleep(2000);
                demo.updateName("张三");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("=============测试结束，这个也很有用！！！==================");

    }
}
