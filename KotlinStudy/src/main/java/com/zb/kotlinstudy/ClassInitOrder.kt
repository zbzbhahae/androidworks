package com.zb.kotlinstudy


/**
 * 初始化顺序
 * 主构造函数里声明的属性
 * 类级别的属性赋值
 * init初始化
 * 次构造函数里的属性赋值和函数调用
 */
class ClassInitOrder(
    var name:String,
    var age:Int
) {

    constructor(name:String) : this(name, 19) {
        p("次构造函数代码块")
        p("$info 在次构造函数中")
        age = 29
    }
    //类级别属性
    var info = "类级别属性"

    init {
        //要使用类级别属性 需要将属性写在init之前
        p("init代码块")
        p("$info 在init中")
    }
//    //类级别属性
//    var info = "类级别属性"

}