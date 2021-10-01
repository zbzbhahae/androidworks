package com.zb.kotlinstudy

/**
 * Sequence
 * 序列不会索引排序它的内容，也不记录元素的数目，使用时，序列里的值可能有无限多
 *序列使用take()来拿到满足条件的多少个
 */


fun main() {
    var a : Sequence<Int> = generateSequence (1) {
        p("序列拿数据了 {$it+2}")
        it+2

    }
    /**
     * take函数如果拿不足其中个数
     * 会一直生成新的去判断，注意死循环
     */
    a.filter { it < 50 }.take(5).toList().p()
//    var i=0
//    var iterator = a.iterator();
//    while (i < 50) {
//        iterator.next().p()
//        i++
//    }

    /**
     * 使用序列获取前1000个素数(质数)
     */
    var b = generateSequence(2){
        it + 1
    }.filter { value->
        (2 until value).map { divider->
            value % divider
        }.none { result-> result==0 }
    }.take(1000).toList().p()
}