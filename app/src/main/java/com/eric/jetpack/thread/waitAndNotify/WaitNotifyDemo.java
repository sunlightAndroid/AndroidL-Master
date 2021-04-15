package com.eric.jetpack.thread.waitAndNotify;

/**
 * @Author: eric
 * @CreateDate: 4/13/21 8:23 PM
 * @Description: java类作用描述
 */
class WaitNotifyDemo {

    private Object object = new Object();
    class runnable1 implements Runnable{
        @Override
        public void run() {
            synchronized (object){

            }
        }
    }



    public void test(){

    }
}
