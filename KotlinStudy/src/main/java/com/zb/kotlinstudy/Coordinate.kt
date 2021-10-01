package com.zb.kotlinstudy
/**
 * 数据类  data修饰 提供了自动生成的tostring
 * 重写了equals、hashcode和copy函数实现
 * **/
data class Coordinate(var x:Int, var y:Int=0, var f:Int = 1) {
    var z = 0

    val isInBounds = x > 0 && y > 0

    constructor(x: Int) : this(x, 10) {
        y = 20
        z = 10
    }

    /**支持重载+号运算符 其他操作符自行看文档*/
    operator fun plus (o2 : Coordinate): Coordinate {
        return Coordinate(x + o2.x, y+o2.y,  f+o2.f)
    }


    override fun toString(): String {
        return "Coordinate(x=$x, y=$y, f=$f, z=$z, isInBounds=$isInBounds)"
    }


}