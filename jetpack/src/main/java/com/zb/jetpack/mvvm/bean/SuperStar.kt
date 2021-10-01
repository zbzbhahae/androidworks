package com.zb.jetpack.mvvm.bean

import android.content.res.Resources
import android.graphics.drawable.Drawable

data class SuperStar(var name:String, var img: Int, var age: Int) {


    companion object {
        @JvmStatic
        fun getDrawableById(id: Int): Drawable {
            return Resources.getSystem().getDrawable(id, null)
        }
    }
}