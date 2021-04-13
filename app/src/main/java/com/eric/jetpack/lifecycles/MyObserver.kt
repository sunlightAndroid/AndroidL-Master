package com.eric.jetpack.lifecycles

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @Author: eric
 * @CreateDate: 2021/1/22 2:33 PM
 * @Description: java类作用描述
 */
class MyObserver() : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.e("====", "ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.e("====", "ON_STOP")
    }
}