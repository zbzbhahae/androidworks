package com.zb.kotlinstudy

open class Human(val age:Int)
class Boy(val name:String , age:Int) : Human(age) {
    override fun toString(): String {
        return "Boy(name='$name', age='$age')"
    }
}
class Man(val name:String , age:Int) : Human(age) {
    override fun toString(): String {
        return "Boy(name='$name', age='$age')"
    }
}


class RandomHuman<T : Human>() {
//    fun <T>random(backup:()->T) : T {
//        val items:List<Human> = listOf(
//            Boy("Jack", 20),
//            Man("Rose", 28)
//        )
//        val random:Human = items.shuffled().first()
//        return if(random is T) { // 泛型不能做类型检查
//            random
//        } else {
//            backup()
//        }
//    }

    inline fun <reified T>random(backup:()->T) : T {
        val items:List<Human> = listOf(
            Boy("Jack", 20),
            Man("Rose", 28)
        )
        val random:Human = items.shuffled().first()
        return if(random is T) { //加了reified关键字 可以进行类型判断
            random
        } else {
            backup() //函数返回值决定泛型T的类型
        }
    }
}

fun main() {
    val createHuman : RandomHuman<Boy> = RandomHuman()
    val b = createHuman.random {
        Boy("default", 29)//函数返回值决定泛型T的类型
    }
    p(b)
}