package com.example.appmvp.views

import com.example.appmvp.models.Post

interface MainView {
    fun setAdapter(posts: MutableList<Post>)


}