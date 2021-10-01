package com.zb.jetpack.service

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.zb.jetpack.observer.LocationObserver
import com.zb.jetpack.utils.P


/**
 * LifecycleService 监听服务生命周期
 */
class GPSService : LifecycleService() {


    var observer : LocationObserver = LocationObserver(this)

    init {
        lifecycle.addObserver(observer)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        P.p("GPS onStart")
    }



}