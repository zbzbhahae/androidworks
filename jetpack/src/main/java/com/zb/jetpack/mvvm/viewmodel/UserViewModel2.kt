package com.zb.jetpack.mvvm.viewmodel


import androidx.databinding.ObservableField
import com.zb.jetpack.mvvm.bean.User

class UserViewModel2 {

    private var oUser = ObservableField<User>()
    init {
        oUser.set(User("ZB"))
    }

    fun getUserName() : String? {
        return oUser.get()?.name
    }

    fun setUserName(name : String) {
        if(name == null || name == getUserName())
            return
        oUser.get()?.name = name
    }



}