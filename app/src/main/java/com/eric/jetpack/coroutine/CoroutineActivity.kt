package com.eric.jetpack.coroutine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.eric.jetpack.R
import com.eric.jetpack.retrofit.service.ApiService
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @Author: eric
 * @CreateDate: 3/24/21 11:49 AM
 * @Description: java类作用描述
 */
class CoroutineActivity : AppCompatActivity(R.layout.activity_coroutine) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun click1(view: View) {
        // 启动一个协程，request1、request2、request3依次执行完成之后更新UI
        GlobalScope.launch(Dispatchers.Main) {
            println("协程正在运行 in ${Thread.currentThread().name}")
            val request1 = request1()
            val request2 = request2(request1)
            val request3 = request3(request2)
            updateUI(request3)
        }
        println("协程已经开始了")
    }


    fun click2(view: View) {
        // 启动一个协程，request1依次执行完成之后request2、request3并行执行，等到request2、request3都执行完成之后更新UI
        GlobalScope.launch(Dispatchers.Main) {
            println("协程正在运行 in ${Thread.currentThread().name}")
            val request1 = request1()

            val async2 = GlobalScope.async { request2(request1) }
            val async3 = GlobalScope.async { request3(request1) }
            updateUI(async2.await(), async3.await())
        }
        println("协程已经开始了")
    }

    fun click3(view: View) {
        GlobalScope.launch(Dispatchers.Main) {

        }
        println("协程已经开始了")

    }

    private suspend fun work(): String {
        return suspendCoroutine {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiService = retrofit.create(ApiService::class.java)
            val query = "1001.2014.3001.5501"
            val id = "-1"
            apiService.getData(id, query)
                .enqueue(object : Callback<ResponseBody?> {
                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        it.resumeWithException(t)
                    }

                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        it.resume(response.body()!!.string())
                    }
                })
        }
    }


    private suspend fun request1(): String {
        delay(1000)
        println("request1 in ${Thread.currentThread().name}")
        return "request1"
    }

    private suspend fun request2(request1: String): String {
        delay(1000)
        println("request2 in ${Thread.currentThread().name}")
        return "request2"
    }

    private suspend fun request3(request2: String): String {
        delay(1000)
        println("request3 in ${Thread.currentThread().name}")
        return "request3"
    }

    private fun updateUI(request3: String) {
        println("updateUI in ${Thread.currentThread().name} result: $request3 ")
    }

    private fun updateUI(request2: String, request3: String) {
        println("updateUI in ${Thread.currentThread().name} result: $request2  --  $request3")
    }

}