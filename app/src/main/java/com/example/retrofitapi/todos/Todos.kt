package com.example.retrofitapi.todos

data class Todos(
    val completed: Boolean,
    val title: String,
    val userId: Int,
    val id: Int
)