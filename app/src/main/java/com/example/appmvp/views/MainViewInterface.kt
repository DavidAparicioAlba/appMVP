package com.example.appmvp.views

import android.view.View
import com.example.appmvp.models.Post

interface Contract{
    interface MainView {
        fun setAdapter(posts: MutableList<Post>?)
        fun setScrollListener(funct: (posts:MutableList<Post>?) -> Unit)
        fun hideProgressBar()
        fun showProgressBar()
        fun handlePosts(posts: MutableList<Post>?)
        fun search(view: View, funct: (word:String?) -> Unit)
    }

    interface Presenter{
        fun onInit()
        fun onDestroy()
    }
}
