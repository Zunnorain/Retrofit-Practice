package com.example.retrofitapi.newsly

data class NewsDataClass(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)