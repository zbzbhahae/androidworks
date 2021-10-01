package com.zb.kotlinapp.ui.acts

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.zb.kotlinapp.R
import com.zb.kotlinapp.databinding.ActivityBtDataBinding
import com.zb.kotlinapp.repository.UserRepository
import com.zb.kotlinapp.viewmodel.DBModel

class XieChengViewModelDataBinding : BaseActivity() {


    private lateinit var binding : ActivityBtDataBinding
    private val dbModel : DBModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityBtDataBinding>(this,
            R.layout.activity_bt_data)
        binding.viewModel = dbModel
        binding.lifecycleOwner = this


        binding.button.setOnClickListener {
            dbModel.getUser("张博", 29)
        }


    }



}