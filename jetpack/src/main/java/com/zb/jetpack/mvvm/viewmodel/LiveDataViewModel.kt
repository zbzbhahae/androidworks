package com.zb.jetpack.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel() {

    var data = MutableLiveData<Int>()
    init {
        data.value = 0
    }

    fun getData() : Int? {
        return data?.value
    }


}