package com.eric.jetpack.liveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eric.jetpack.R
import com.eric.jetpack.lifecycles.MyObserver
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {
    private lateinit var viewModel: LiveDataVModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        initListener()
        viewModel = ViewModelProvider(this).get(LiveDataVModel::class.java)
        val liveData = viewModel.getLike()
        liveData.observe(this, Observer<Int> {
            tv_star_count.text = "当前点赞：${it}"
        })
    }
    private fun initListener() {
        tv_star.setOnClickListener {
            viewModel.star()
        }
        tv_down.setOnClickListener {
            viewModel.down()
        }
    }
}