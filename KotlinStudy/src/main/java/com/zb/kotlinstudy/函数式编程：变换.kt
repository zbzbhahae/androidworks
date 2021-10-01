package com.zb.kotlinstudy

/**
 * map  和  flatMap
 * 会遍历集合内容
 *
 * */
/**
 * map:
 * 返回一个列表，
 * 其中包含将给定的 [transform] 函数应用于原始集合中的每个元素的结果
 *
 * flatmap:
 * 操作一个集合的集合，将其中多个集合中的元素合并后
 * 返回一个包含所有元素的单一集合
 */
fun main() {
    var list = listOf("a", "b", "c")
    var newList = list.map {
        it.uppercase().p()
    }
    list.p()
    newList.p()

    var list2 = listOf(0..5, 3..8, 9..15)
    var newList2 = list2.flatMap { it }
    list2.p()
    p("")
    newList2.p()
}