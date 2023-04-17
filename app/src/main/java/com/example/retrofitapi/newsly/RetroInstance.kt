package com.example.retrofitapi.newsly

import com.example.retrofitapi.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.NEWS_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    val newsInterface:NewsInterface by lazy {
        retrofit.create(NewsInterface::class.java)
    }
}