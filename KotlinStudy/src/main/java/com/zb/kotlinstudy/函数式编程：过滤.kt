package com.zb.kotlinstudy

/**
 *filter 返回true的添加到新集合
 *  注意 2..5 是2、3、4、5
 *  2 to 5 = 2、3、4、5
 *  2 until 5 = 2、3、4
 *
 *  .none 如果没有元素与给定的 [predicate] 匹配，则返回 `true`。
 *  .none 等同于 .filter{xxx}.isEmpty()
 * */


fun main() {
    var list = listOf(0..5, 3..8, 9..15)
    var newList = list.flatMap { it }

    newList = newList.filter {
        it > 3
    }
    newList.p()
    newList = list.flatMap { it }.filter {
        item->
        (2 until item).map {
            item % it
        }.none { resultItem -> resultItem == 0 }
    }.p()

    (2 to 5).p()
}
