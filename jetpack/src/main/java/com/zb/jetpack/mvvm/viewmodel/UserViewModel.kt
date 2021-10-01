package com.zb.jetpack.mvvm.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.zb.jetpack.BR
import com.zb.jetpack.mvvm.bean.User
import com.zb.jetpack.utils.P

class UserViewModel : BaseObservable() {

    private var user = User("ZB")

    @Bindable
    fun getUserName(): String {
        P.p("getUserName")
        return user.name
    }

    fun setUserName(name: String) {
        P.p("setUserName $name")
        if(name == null || name == user.name)
            return
        user.name = name
        notifyPropertyChanged(BR.userName)
    }

}