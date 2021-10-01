package com.zb.kotlinstudy

import java.util.*

class QianTaoLei {


    companion object {
        var inn = Inner()
        fun showInnerInfo() {
            inn.showInfo();
        }
    }
    /**
     * 如果一个类只对另外一个类有用，那么可以将这个类写在一起
     */
    class Inner {
        val num = Random().nextInt()
        val info = "我是一个嵌套类里面的类  id $num"
        fun showInfo() {
            p(info)
        }
    }
}