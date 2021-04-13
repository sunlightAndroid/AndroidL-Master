package com.eric.jetpack.lifecycles

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @Author: eric
 * @CreateDate: 2/25/21 3:47 PM
 * @Description: java类作用描述
 */
class MyChronometer(context: Context?, attrs: AttributeSet?) : Chronometer(context, attrs),
    LifecycleObserver {
    var lastTimeDiff = 0L

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onResume() {
        start()
        base = SystemClock.elapsedRealtime() - lastTimeDiff
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        stop()
        lastTimeDiff = SystemClock.elapsedRealtime() - base
    }
}