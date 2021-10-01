package com.zb.jetpack

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.zb.jetpack.observer.AppObserver

/**
 * ProcessLifecycle监听程序生命周期
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppObserver())
    }
}