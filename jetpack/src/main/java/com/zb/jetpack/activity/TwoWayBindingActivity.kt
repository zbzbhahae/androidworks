package com.zb.jetpack.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zb.jetpack.R
import com.zb.jetpack.databinding.ActivityTwoWayBindingBinding
import com.zb.jetpack.mvvm.viewmodel.UserViewModel

class TwoWayBindingActivity : BaseActivity() {

    private lateinit var binding : ActivityTwoWayBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityTwoWayBindingBinding>(this, R.layout.activity_two_way_binding)
        binding.userViewModel = UserViewModel()
    }
}