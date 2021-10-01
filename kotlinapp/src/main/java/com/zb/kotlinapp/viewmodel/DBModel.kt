package com.zb.kotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zb.kotlinapp.bean.User
import com.zb.kotlinapp.repository.UserRepository
import kotlinx.coroutines.launch

class DBModel : ViewModel() {
    val liveData = MutableLiveData<User>()
    private val userRepository = UserRepository()

    fun getUser(name:String, age:Int) {
        viewModelScope.launch {
            liveData.value = userRepository.getUser(name, age)
        }
    }
}