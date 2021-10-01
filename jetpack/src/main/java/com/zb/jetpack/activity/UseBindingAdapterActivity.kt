package com.zb.jetpack.activity


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zb.jetpack.R
import com.zb.jetpack.databinding.ActivityUseBindingAdapterBinding

class UseBindingAdapterActivity : BaseActivity() {

    private lateinit var binding : ActivityUseBindingAdapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_use_binding_adapter)

        binding.imageUrlInXML = "https://img1.baidu.com/it/u=1540482440,3576916641&fm=26&fmt=auto&gp=0.jpg"
        binding.localImageInXML = R.mipmap.zhouxingchi
    }
}