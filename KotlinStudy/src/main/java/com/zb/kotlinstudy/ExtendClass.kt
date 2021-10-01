package com.zb.kotlinstudy

open class ExtendClass() {
    open fun load() {
        p("load()")
    }
}

class Child : ExtendClass() {
    override fun load() {
        super.load()
        p("load from child")
    }

    fun release() {
        p("release from child")
    }
}