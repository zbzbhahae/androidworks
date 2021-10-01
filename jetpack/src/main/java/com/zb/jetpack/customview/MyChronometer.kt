package com.zb.jetpack.customview

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyChronometer(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    Chronometer(context, attrs, defStyleAttr, defStyleRes), LifecycleObserver {

    private var elapsedTime = 0L

        constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0, 0) {
        }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startMeter() {
        start()
        base = SystemClock.elapsedRealtime() - elapsedTime
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopMeter() {
        stop()
        elapsedTime = SystemClock.elapsedRealtime() - base
    }

}