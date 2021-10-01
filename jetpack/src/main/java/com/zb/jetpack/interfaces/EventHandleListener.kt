package com.zb.jetpack.interfaces

import android.content.Context
import android.view.View
import android.widget.Toast
import com.zb.jetpack.utils.P

class EventHandleListener {

    private var context: Context? = null

    constructor(context: Context)  {
        this.context = context
    }

    fun buttonOnClick(view: View) {
        P.p("buttonOnClick")
        Toast.makeText(context, "喜欢", Toast.LENGTH_SHORT).show()
    }

}