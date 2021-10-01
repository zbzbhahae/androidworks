package com.zb.kotlinapp.repository

import com.zb.kotlinapp.bean.User

class UserRepository {

    suspend fun getUser(name:String, age:Int) : User {
        return User(name, age)
    }
}