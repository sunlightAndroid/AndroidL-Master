package com.eric.jetpack.viewModel

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eric.jetpack.R
import com.eric.jetpack.annotation.AnnotationSample
import com.eric.jetpack.coroutine.CoroutineActivity
import com.eric.jetpack.lifecycles.LifecycleActivity
import com.eric.jetpack.liveData.LiveDataActivity
import com.eric.jetpack.retrofit.RetrofitActivity
import com.eric.jetpack.thread.ThreadActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model = ViewModelProvider(this, MainVmodelFactory("2222")).get(MainVModel::class.java)

        tv_text.text = model.text.toString() + "  id:" + model.id

        tv_text.setOnClickListener {
            model.text++
            tv_text.text = model.text.toString()
        }

        initListener()
    }


    private fun initListener() {
        tv_lifeCycles.setOnClickListener {
            val intent = Intent(this, LifecycleActivity::class.java)
            startActivity(intent)
        }

        tv_liveData.setOnClickListener {

            val intent = Intent(this, LiveDataActivity::class.java)
            startActivity(intent)
        }

        tv_retrofit.setOnClickListener {
            val intent = Intent(this, RetrofitActivity::class.java)
            startActivity(intent)
        }

        tv_thread.setOnClickListener {
            val intent = Intent(this, ThreadActivity::class.java)
            startActivity(intent)
        }


        tv_coroutine.setOnClickListener {
            val intent = Intent(this, CoroutineActivity::class.java)
            startActivity(intent)
        }

    }

}