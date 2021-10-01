package com.zb.kotlinstudy

import java.io.File


fun <T> T.applyy(block : (T)->Unit) : T {
    block(this)
    return this
}

/**
 * public inline fun <T> T.apply(block: T.() -> Unit): T
 *   T.()  注意这个入参  可以使入参函数能够调用apply调用者本身this
 *   (T)->Unit 有同样效果 但是在入参中要使用it.xxx 而T.() 直接调用函数而不写this和it
 *   这种写法是DSL
 */


fun main() {
    var f = File("D:\\test.txt")
    f.apply {
        setReadable(true)
        setExecutable(true)
        p(readText(Charsets.UTF_8))
        Thread.sleep(55555555)
    }
    f.applyy {
        it.setExecutable(true)
    }
}