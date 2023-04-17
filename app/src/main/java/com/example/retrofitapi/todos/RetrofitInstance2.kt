package com.example.retrofitapi.todos

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance2 {

    private val ret by lazy {
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val todoApi: TodoApi by lazy {
        ret.create(TodoApi::class.java)
    }
}