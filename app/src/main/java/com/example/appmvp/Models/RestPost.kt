package com.example.appmvp.models


import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface RestPost {
    @GET("posts")
    fun getPostsData(): Call<List<Post>>

    companion object {

        var BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create() : RestPost {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RestPost::class.java)

        }
    }
}