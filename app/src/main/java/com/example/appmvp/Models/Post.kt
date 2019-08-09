package com.example.appmvp.Models

// nunca poner exclamacion
data class Post(
    val userId: Int,
    val id: Int,
    val title: String?,
    val body: String?
)