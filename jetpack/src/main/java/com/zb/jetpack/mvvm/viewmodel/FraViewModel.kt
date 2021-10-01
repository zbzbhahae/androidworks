package com.zb.jetpack.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FraViewModel : ViewModel() {

    private var progress : MutableLiveData<Int>? = null


    fun getProgressData() : MutableLiveData<Int>? {
        if(null == progress) {
            progress = MutableLiveData<Int>()
            progress!!.value = 0
        }

        return progress
    }

}