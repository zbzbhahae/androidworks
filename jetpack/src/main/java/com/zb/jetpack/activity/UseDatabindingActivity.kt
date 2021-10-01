package com.zb.jetpack.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.zb.jetpack.R
import com.zb.jetpack.databinding.ActivityUseDataBindingBinding
import com.zb.jetpack.interfaces.EventHandleListener
import com.zb.jetpack.mvvm.bean.SuperStar

class UseDatabindingActivity : com.zb.jetpack.activity.BaseActivity() {

    private lateinit var binding : ActivityUseDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_use_data_binding)
        binding.star = SuperStar("周星驰", R.mipmap.zhouxingchi, 58)
        binding.eventHandle = EventHandleListener(this)
//        this.findViewById<ImageView>(R.id.image).setImageResource(R.mipmap.ic_launcher)
    }
}