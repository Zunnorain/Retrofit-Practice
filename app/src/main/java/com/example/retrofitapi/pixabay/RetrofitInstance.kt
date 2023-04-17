package com.example.retrofitapi.pixabay

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val ret by lazy {
        Retrofit.Builder().baseUrl("https://pixabay.com/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiInterface: ApiInterface by lazy {
        ret.create(ApiInterface::class.java)
    }
}