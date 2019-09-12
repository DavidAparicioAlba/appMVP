package com.example.appmvp.views

import com.example.appmvp.models.Post
interface contract{
    interface MainView {
        fun setAdapter(posts: MutableList<Post>?)
        fun setScrollListener(funct: (posts:Int) -> Unit)
        fun hideProgressBar()
        fun showProgressBar()
        fun handlePosts(posts: MutableList<Post>?)
    }

    interface Presenter{
        fun onInit()
        fun onDestroy()
    }
}
