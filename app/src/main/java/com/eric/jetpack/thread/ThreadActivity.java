package com.eric.jetpack.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eric.jetpack.R;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: eric
 * @CreateDate: 3/24/21 10:32 AM
 * @Description: java类作用描述
 */
public class ThreadActivity extends AppCompatActivity {
    private Handler threadHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    public void main_to_work(View view) {
        startThread();
    }

    private void startThread() {
        LooperThread thread = new LooperThread("looper-thread 1");
        thread.start();

        threadHandler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                System.out.println("主线程发送过来的消息：" + msg.what);
                System.out.println("当前线程 ：" + Thread.currentThread().getName());
            }
        };

        threadHandler.sendEmptyMessage(11111);
    }

    class LooperThread extends Thread {

        private Looper looper;

        public LooperThread(String name) {
            super.setName(name);
        }

        public Looper getLooper() {
            synchronized (this) {
                if (looper == null && isAlive()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return looper;
        }

        @Override
        public void run() {
            Looper.prepare();
            synchronized (this) {
                looper = Looper.myLooper();
                notify();
            }
            System.out.println("我在后台执行任务");
            threadHandler.sendEmptyMessage(11111);
            Looper.loop();

        }
    }


    //------------线程的暂停与恢复start---------------//
    private ReentrantLock lock = new ReentrantLock();
    private volatile boolean isPause = false; // 是否暂停线程
    private Condition pauseCondition = lock.newCondition();
    public void resumeThread(View view) {
        isPause = false;
        try {
            lock.lock();
            pauseCondition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void pauseThread(View view) {
        isPause = true;
    }

    class Runnable1 implements Runnable{
        @Override
        public void run() {
            sing();
        }
    }

    private synchronized void sing(){
        if(isPause){
            System.out.println("===============pause===========");
            lock.lock();
            try {
                pauseCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        System.out.println("===============resume===========");

        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + " 开始搬砖了");
            Thread.sleep(2000);
            System.out.println(name + " 搬砖结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startWork(){
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable1()).start();
        }
    }

    public void createMoreTask(View view) {
        startWork();
    }
    //------------线程的暂停与恢复end---------------//




}
