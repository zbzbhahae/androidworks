package com.zb.kotlinstudy

/**
 * 不用修改类定义的情况下增加类的功能
 * 无法解除或继承某个类时使用(没有open)
 * **/

/**
 * 给String类添加一个addString方法
 */
infix fun String.addString(str1:String) : String {
    if(str1.isNullOrBlank())
        return this

    return "$this$str1"
}

/**
 * 给所有类添加方法
 */
fun <T> T.p():T{
    p(this.toString())
    return this
}

/**
 * private后其他地方访问不到
 * 否则是全局 默认public
 */
private fun String.info() {
    p(this.toString())
}

fun main() {
    var a = "abc"
    p(a.addString("def").p().p())
    a.p()
    p(a.plus("111"))

}

