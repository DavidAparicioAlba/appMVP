package com.example.appmvp.services.repo


import com.example.appmvp.models.Post
import com.example.appmvp.services.RestPost
import retrofit2.Callback


class Repository(private val type:String, private val operation: String) {

    fun fetch(param: Callback<MutableList<Post>>) {

        when (type) {
            "Retrofit" -> {
                when (operation) {
                    "get" -> {
                        RestPost.create().getPostsData()
                            .enqueue(param)

                    }
                }
            }
        }
    }
}