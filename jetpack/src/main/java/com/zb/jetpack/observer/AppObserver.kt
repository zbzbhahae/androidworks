package com.zb.jetpack.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.zb.jetpack.utils.P


/**
 * ProcessLifeCycle
 * 针对整个应用程序监听，与activity无关
 * onCreat只会调用一次，onDestory永远不会调用
 */
class AppObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        P.p("Application  onCreate")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        P.p("Application  onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        P.p("Application  onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory() {
        //onDestory永远不会调用
        P.p("Application  onDestory")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        P.p("Application  onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        P.p("Application  onStop")
    }
}