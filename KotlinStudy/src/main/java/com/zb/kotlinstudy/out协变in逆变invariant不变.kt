package com.zb.kotlinstudy

/**
 * out协变
 * 如果泛型类型只将泛型作为函数的返回(输出)
 * 那么使用out可以称之为生产类/接口
 * 因为它主要是用来生产(produce)指定的泛型对象
 */

/**
 * in逆变
 * 如果泛型类型只将泛型类型作为函数的入参(输入)
 * 那么使用in,可以称之为消费者/接口
 * 因为它主要是用来消费(consume)指定的泛型对象
 */

/**
 * invariant不变
 * 如果泛型类型既将泛型类型作为函数参数
 * 又将泛型类型作为函数的输出
 * 那么就用他(默认是invariant)
 */

interface Production<out T> {
//    fun produced(item : T) : T//不能输入
    fun produced() : T
}

interface Consumer<in T> {
//    fun consume(item : T) : T   //不能输出
    fun consume(item : T)
}

interface ProductionConsumer<T> {
    fun product() : T
    fun consume(item:T)
}


open class Food
open class FastFood : Food()
open class Burger : FastFood()


//汉堡生产者
class FoodFactory : Production<Food> {
    override fun produced(): Food {
        p("produce food")
        return Food()
    }
}

class FastFoodFactory : Production<FastFood> {
    override fun produced(): FastFood {
        p("produce fastfood")
        return FastFood()
    }
}

class BurgerFactory : Production<Burger> {
    override fun produced(): Burger {
        p("produce burger")
        return Burger()
    }
}

/*****************************************/
class FoodEater : Consumer<Food> {
    override fun consume(item: Food) {

    }
}
class FastFoodEater : Consumer<FastFood> {
    override fun consume(item: FastFood) {

    }
}
class BurgerEater : Consumer<Burger> {
    override fun consume(item: Burger) {

    }
}



fun main() {
    var a : Production<Food> = FoodFactory()

    /**
     * 子类泛型对象可以赋值给父类泛型对象
     * java中不能这样做
     * 如果去掉out也不行
     */
    var b : Production<Food> = FastFoodFactory()

    /**
     * 父类泛型对象可以赋值给子类泛型对象
     * java中不能这样做
     * 如果去掉in也不行
     */
    var c : Consumer<Burger> = FastFoodEater();
    var d : Consumer<Burger> = FoodEater()
//    var e : Consumer<Food> = BurgerEater() //错误
}