package com.zb.kotlinstudy

class BanShengDuiXiang {
    /**
     * 如果你想将某个对象的初始化和一个雷的实例捆绑在一起，可以考虑使用伴生对象
     * companion作为修饰符   你可以在一个类里声明一个伴生对象，一个类里只能有一个半生对象
     * 只有调用类的伴生对象或者创建类的实例对象 伴生对象才会实例化 并且只有一份
     * 相当于java中的static方法、属性
     * **/
    companion object {
        const val info = "我是一个伴生对象"
        fun showInfo() {
            p(info)
        }
    }
}