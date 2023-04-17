package com.example.retrofitapi.pixabay

data class PixabyModelPhoto(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)