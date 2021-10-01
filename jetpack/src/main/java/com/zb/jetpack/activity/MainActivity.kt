package com.zb.jetpack.activity


import android.os.Bundle
import com.zb.jetpack.R
import com.zb.jetpack.customview.MyChronometer

class MainActivity : com.zb.jetpack.activity.BaseActivity() {

    private lateinit var chronometer : MyChronometer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        chronometer = findViewById(R.id.main_chronometer)
        lifecycle.addObserver(chronometer)




    }



}