package com.example.appmvp.services


import com.example.appmvp.models.Post
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call as Call1


interface RestPost {
    @GET("posts")
    fun getPostsData(): Call1<MutableList<Post>>

    companion object {

        private var BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create() : RestPost {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RestPost::class.java)

        }
    }
}