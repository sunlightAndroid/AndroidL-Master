package com.eric.jetpack.thread.joinAndSleep;

/**
 * <pre>
 *     author : eric
 *     time   : 2021/04/15
 *     desc   :
 *     version:
 * </pre>
 */
class JoinDemo {

    private void test(){
        Thread thread = new Thread(new runnable1());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===================3");

    }

    class runnable1 implements Runnable{

        @Override
        public void run() {
            System.out.println("===================1");
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===================2");
        }
    }


    public static void main(String[] args) {
        JoinDemo demo = new JoinDemo();
        demo.test();
    }
}
