package com.zb.kotlinapp.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface Api {
    @GET(" ")
    suspend fun getInfo() : String

    @GET(" ")
    fun getInfo2() : Call<String>
}

val serviceApi : Api by lazy {
    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    val retrofit = Retrofit.Builder().client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.baidu.com")
        .build()
    retrofit.create(Api::class.java)
}
