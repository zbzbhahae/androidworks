package com.zb.kotlinstudy

/**
 * 让你的类支持解构语法
 */
class JieGouYuFa(var name:String, var age:Int) {

    //用于支持解构语法 顺序要对 从1开始 data数据类自带支持解构
    operator fun component1() = name
    operator fun component2() = age
}

fun main() {
    var (a, b) = JieGouYuFa("zb", 29)
    p(a)
    p(b)
}