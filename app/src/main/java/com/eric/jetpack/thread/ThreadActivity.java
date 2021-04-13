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

/**
 * @Author: eric
 * @CreateDate: 3/24/21 10:32 AM
 * @Description: java类作用描述
 */
public class ThreadActivity extends AppCompatActivity {
    private Handler UIHandler;
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

        threadHandler = new Handler(thread.getLooper()){
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
            synchronized (this){
                looper = Looper.myLooper();
                notify();
            }
            System.out.println("我在后台执行任务");
            threadHandler.sendEmptyMessage(11111);
            Looper.loop();

        }
    }

}
