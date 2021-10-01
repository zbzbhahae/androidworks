package com.zb.kotlinstudy

fun <T> Iterable<T>.randomTake() : T {
    return this.shuffled().first()
}