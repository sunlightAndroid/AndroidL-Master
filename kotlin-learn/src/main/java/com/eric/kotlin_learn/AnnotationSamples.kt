package com.eric.kotlin_learn

/**
 * @Author: eric
 * @CreateDate: 3/15/21 1:01 PM
 * @Description: java类作用描述
 */

fun main() {
    request(HomeApi())
}

enum class Method() {
    GET,
    POST,
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpMethod(val method: Method)


@HttpMethod(Method.GET)
class HomeApi {
    fun getData() {
        println("拉取首页接口")
    }
}

fun request(api: HomeApi){
    val annotations = api.javaClass.annotations
    val httpMethod  =  annotations.find { it is HttpMethod } as HttpMethod
     println("需要通过${httpMethod.method}获取网络数据")
}