package com.eric.jetpack.lifecycles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eric.jetpack.R
import kotlinx.android.synthetic.main.activity_lifecycles.*

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycles)
        lifecycle.addObserver(MyObserver())

        lifecycle.addObserver(mChronometer);
    }

}