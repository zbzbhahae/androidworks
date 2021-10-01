package com.zb.kotlinstudy

/**
 * 密封类 比enum更复杂需求使用
 * 类里包含一组继承于密封类的类型 写在同一文件中
 * 注意object 与class 标识
 * 判断时使用is
 */
sealed class MiFengLei {

    object EAST : MiFengLei()
    object WEST : MiFengLei()
    object NORTH : MiFengLei()
    class SOUTH(val speed:Int) : MiFengLei()


}

class boatController2(var dir:MiFengLei) {
    fun getDirection() : String {
        return when(dir) {
            is MiFengLei.EAST->"向东"
            is MiFengLei.WEST->"向西"
            is MiFengLei.SOUTH->"向南 速度为:${(dir as MiFengLei.SOUTH).speed} 公里每小时"
            is MiFengLei.NORTH->"向北"
            else -> "未知"
        }
    }
}

fun main() {
    var boatC = boatController2(MiFengLei.SOUTH(200))
    p(boatC.getDirection())
}