package com.zb.jetpack.utils

import android.util.Log

class P {

    companion object {
        fun<T> p(info : T) {
            Log.e("kotlin", info.toString())
        }
    }
}