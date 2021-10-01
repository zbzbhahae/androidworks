package com.zb.kotlinstudy

import java.util.*


//object修饰的类 只能创建一次 也就是单例
//类名也是创建出的唯一对象名字
object ObjectClass {

    var a:Int = Random().nextInt()

    fun showInfo() {
        p("我是一个只能产生一次的对象  里面的a是:$a")
    }
}