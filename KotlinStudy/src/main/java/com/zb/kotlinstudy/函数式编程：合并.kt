package com.zb.kotlinstudy

/**
 *zip   list的方法
 *  注意 list合并返回的是map
 *  map的size是两个list的最小大小
 * */

/**
 * fold 累加器
 * 接受一个初始累加器值，随后会根据匿名函数的结果更新
 */
fun main() {
    var list = listOf(1, 2, 3)
    var list2 = listOf("A", "B", "C", "D")

    var list3 = list.zip(list2).p()

//    var map = mapOf((1 to "A"), (2 to "B"), (3 to "C"))
//    var map2 = mapOf(("a" to 1), ("b" to 2), ("c" to 3), ("d" to 4))

    list.fold(0f) { adder : Float, item->
        adder + item * 1.1f
    }.p()
}
