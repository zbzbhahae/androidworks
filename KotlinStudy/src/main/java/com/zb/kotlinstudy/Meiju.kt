package com.zb.kotlinstudy

enum class Meiju {
    EAST,
    WEST,
    NORTH,
    SOUTH;

    var speed:Int? = null;
}

enum class Meiju2(private val coordinate: Coordinate) {
    EAST(Coordinate(1,0)),//枚举可以设置数值 其实就是枚举对象调用构造方法
    WEST(Coordinate(1,1)),
    NORTH(Coordinate(0,1)),
    SOUTH(Coordinate(0,0));

    fun updateCoordinate(personCoordinate:Coordinate) : Coordinate {
        return Coordinate(personCoordinate.x, personCoordinate.y)
    }
}

class BoatController(var dir:Meiju) {

    fun getCurrentDirection() : String {
        return when(dir) {
            Meiju.EAST->"向东"
            Meiju.NORTH->"向北"
            Meiju.WEST->"向西"
            Meiju.SOUTH->"向南"
            else -> "未知"
        }
    }
}