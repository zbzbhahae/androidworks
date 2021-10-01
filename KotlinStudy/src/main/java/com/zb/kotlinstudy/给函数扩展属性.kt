package com.zb.kotlinstudy

/**
 * 除了给类添加扩展函数 还能给类添加扩展属性
 *
 * **/

val String.infoo : String
    get() = "$this infos"
//    set(value) {
//        infoo = value
//    }

/**
 * 可空类型拓展
 * 解决可能出现的空值问题
 * infix关键字适用于有单个参数的扩展和类函数，可以让你更简洁的调用函数
 * 如果一个函数是infix的 那么调用它时，
 * 接受者和函数之间的点以及参数的括号可以不要
 */
infix fun String?.pWithDefault(default:String) {
    p(this?:default)
}


fun main() {
    "abcde".infoo.p()
    var a = "abcde"
//    a.infoo = "info from outside"
//    a.infoo.p()
    "fdasfas".contains("f").p()
    var c:String? = null;
    c.pWithDefault("default")
    c.plus("a")
    println(c)

    p("ddd" addString "eee")
}

