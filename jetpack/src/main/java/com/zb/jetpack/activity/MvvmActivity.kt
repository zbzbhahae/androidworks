package com.zb.jetpack.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.zb.jetpack.R
import com.zb.jetpack.databinding.ActivityMvvmBinding
import com.zb.jetpack.mvvm.viewmodel.ScoreViewModel

class MvvmActivity : BaseActivity() {

    lateinit var binding: ActivityMvvmBinding
    lateinit var viewModel : ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)

        viewModel = ViewModelProvider(this, ViewModelProvider
            .AndroidViewModelFactory(application)
        ).get(ScoreViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this



    }
}