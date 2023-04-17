package com.example.retrofitapi.todos

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {

    @GET("/todos")
    suspend fun getData(): Response<List<Todos>>

}