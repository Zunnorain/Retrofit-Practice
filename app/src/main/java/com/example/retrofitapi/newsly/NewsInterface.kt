package com.example.retrofitapi.newsly

import com.example.retrofitapi.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=dcc6bcb200e243d8a0292cba76b992af


interface NewsInterface {

    @GET("v2/top-headlines?country=us&category=business&apiKey=${Constants.NEWSAPI}")
    fun getNews(
        @Query("country") country:String,
        @Query("page") page: Int
    ): Call<NewsDataClass>
}