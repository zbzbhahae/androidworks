package com.zb.kotlinstudy

interface Movable {

    var maxSpeed:Int
    var wheels:Int

    fun move() : String
}

/**所有接口的属性和函数实现都要用override关键字**/
class Car(var name:String, override var wheels: Int = 4) : Movable {

    override var maxSpeed: Int = 280

    override fun move(): String {
        return "Car:$name is moving~"
    }

}

abstract class Carlike(var name: String) : Movable {

    fun openDoor() {
        p("door is open!")
    }
}

class SixWheelCar(_name:String) : Carlike(_name), Drivable {


    override var maxSpeed: Int
        get() = 333
        set(value) {}
    override var wheels: Int
        get() = 6
        set(value) {}

    override fun move(): String {
        p("$name is moving")
        return "moving"
    }

    override val angleSpeed: Int = 22

    override fun driveLeft() {
        p("$name is driving left")
    }

    override fun driveRight() {
        p("$name is driving right")
    }

}

interface Drivable {
    val angleSpeed : Int
    fun driveLeft()
    fun driveRight()
}

/**
 * 泛型类
 * **/
class InfoPrint<T> (item : T) {
    private var subject : T = item

    fun showInfo() {
        p("T's info is ${subject.toString()}")
    }

    fun getT() : T? {
        return subject
    }

    fun getInfoWithWrap(wrapper : String) : String {
        return  "  $wrapper  +  ${subject.toString()}"
    }

    /**
     * 多个泛型参数
     * fetch 接受一个函数作为参数  这个接受的函数的参数是T类型 返回值是R类型
     * 然后fetch的返回值是R类型
     * **/
    fun <R> fetch(subjectFuntion: (T)->R) : R {
        return subjectFuntion(subject)
    }

    fun <R> fetch2(subjectFunction:(T, Int)->R) : R {
        return subjectFunction(subject, 29)
    }

    fun <V : String>fetch3(subjectFuntion:(T, Int)->V) : V {
       return subjectFuntion(subject, 29)
    }
}

/**
 * vararg  可以传多个实例
 * **/
class InfoPrinter<T> (vararg item : T) {
    private var subject : Array<out T> = item

    fun showInfos() {
        subject.forEach {
            p("$it")
        }
    }

    operator fun get(index : Int) : T {
        return subject[index]
    }
}






fun main() {
    var a = Car("四驱车")
    var b = SixWheelCar("超级牛车")
    b.move()

    var c = InfoPrint<String>("哈哈")
    c.showInfo()
    p(c.getInfoWithWrap("你在笑吗？ "))
    var result = c.fetch {
        //dosomething 只要代码块中返回it的类型就行
        var dd = 123
        addStr(it, 29)
    }

    p(result)

    result = c.fetch2(::addStr)
    result = c.fetch2 { str, age -> //双参数版本
        addStr(str, age) }
    p(result)

    /**
     * 可变参数会在函数体中，自动转变为数组类型
     * 数组类型不能作为参数，直接传递给可变参数
     * 在数组类型前面添加 * ，可以传递给可变参数
     * **/
    var ccc = arrayOf("a","b","1","2")
    var d = InfoPrinter("a", "b", "c", "d")
    d = InfoPrinter(*ccc)

    d.showInfos()

}

fun addStr(str:String, age:Int) : String {
    return "add some string  $str  age is $age"
}
fun addNum(num:Int) : Int {
    return num + 1;
}