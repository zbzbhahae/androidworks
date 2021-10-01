package com.zb.jetpack.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.zb.jetpack.R
import com.zb.jetpack.service.GPSService

class ServiceLifeCycleActivity : com.zb.jetpack.activity.BaseActivity() , View.OnClickListener {

    lateinit var btn1 : Button
    lateinit var btn2 : Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_lifecycle)


        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)



        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }
    }

    override fun onClick(v: View?) {
        when(v) {
            btn1->{startService(Intent(this, GPSService::class.java))}
            btn2->{stopService(Intent(this, GPSService::class.java))}
            else->{}
        }
    }
}