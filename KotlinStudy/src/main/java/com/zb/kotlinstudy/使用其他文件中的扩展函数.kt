package com.zb.kotlinstudy

/**
 * 引入扩展文件
 * 甚至可以起别名
 */
import com.zb.kotlinstudy.randomTake as randomP

fun main() {
    val list = listOf("a", "b", "c", "d")
    list.randomP().p()
}