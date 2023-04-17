package com.example.retrofitapi.pixabay

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/")
    suspend fun getQuotes(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("image_type") imageType: String
    ): Response<PixabyModelPhoto>

}