package com.eric.kotlin_learn

/**
 * @Author: eric
 * @CreateDate: 3/12/21 1:38 PM
 * @Description: java类作用描述
 */

fun main() {
    val result = arrayOf(1, 2, 3, 4, 5).toSum {
        println("当前的元素 $it")
    }
    println("计算结果 $result")

    println("==========end===========")

    val result2 = arrayOf(1, 2, 3).toSum()(2)
    println("计算结果 $result2")
    println("==========end===========")


    testClosure(1)(2) {
        println("结果为 ${it}")
    }
    println("==========end===========")

    literal()
    println("==========end===========")

    test("111",1,2,3)
}


fun test(b: String ,vararg a: Int ) {
    println(a.toString())
}


/**
 *  对一个数组求和，同时还能回调当前的每一个元素
 *   参数作为返回值
 */
fun Array<Int>.toSum(callback: (item: Int) -> Unit): Int {
    var sum = 0
    for (i in this) {
        sum += i
        callback(i)
    }
    return sum
}

/**
 *  对一个数组求和 然后乘以缩放系数scale
 *  方法作为返回值
 */
fun Array<Int>.toSum(): (scale: Int) -> Int {
    return fun(scale: Int): Int {
        println("第一层函数")
        var sum = 0
        for (i in this) {
            sum += i
        }
        return sum * scale
    }
}

/**
 *  闭包
 *  需求：实现一个接受一个testClosure方法，该方法要接受-一个Int类型的v1参数
 *  同时能够返回一个声明为 (v2: Int, (Int) -> Unit) -> Unit的函数，并且计算v1 v2的和
 */
fun testClosure(v1: Int): (v2: Int, (Int) -> Unit) -> Unit {
    return fun(v2: Int, printer: (Int) -> Unit) {
        printer(v1 + v2)
    }
}

/**
 *  字面值
 */
fun literal() {
    val temp: (num: Int) -> Boolean
    temp = { num -> num > 10 }
    println(temp(9))
}

