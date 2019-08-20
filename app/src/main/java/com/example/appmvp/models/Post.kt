package com.example.appmvp.models

// nunca poner exclamacion
data class Post(
    val userId: Int,
    val id: Int,
    val title: String?,
    val body: String?
)