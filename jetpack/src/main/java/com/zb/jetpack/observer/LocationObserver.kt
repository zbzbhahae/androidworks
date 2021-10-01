package com.zb.jetpack.observer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.zb.jetpack.utils.P

class LocationObserver(var context: Context) : LifecycleObserver {

    private val locationListener = MyLocationListener()
    private lateinit var locationManager: LocationManager

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startGetLocation() {
        P.p("GPS服务启动了")
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager


        /**
         * adb -s emulator-5554 emu geo fix 121.445123 31.140109
         * adb -s emulator-5554 emu geo fix 121.495123 31.240109
         */

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        3000, 1f, locationListener)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopGetLocation() {
        locationManager.removeUpdates(locationListener)
        P.p("GPS服务关闭了")
    }

    class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            P.p(location.toString())
        }

    }

}