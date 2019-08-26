package com.example.appmvp.presenters


import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvp.models.AdapterRecycler
import com.example.appmvp.models.Post
import com.example.appmvp.services.RestPost
import com.example.appmvp.views.MainActivity
import retrofit2.Callback
import retrofit2.Response


class MainPresenter(var mainActivity: MainActivity) {

    var posts:MutableList<Post>? = mutableListOf()
    var showPosts:MutableList<Post>? = mutableListOf()

    fun onLoad(mainListView: RecyclerView) {
        RestPost.create().getPostsData().enqueue(object : Callback<MutableList<Post>> {
            override fun onFailure(call: retrofit2.Call<MutableList<Post>>?, t: Throwable?) {
                Toast.makeText(mainActivity, "failed response", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: retrofit2.Call<MutableList<Post>>?,
                response: Response<MutableList<Post>>?
            ) {
                val res = response?.body()
                posts = res
                if(showPosts!!.size<posts!!.size){
                    fetch10(posts!!)
                    mainListView.layoutManager = LinearLayoutManager(mainActivity)
                    mainListView.adapter =
                        response?.body()?.let { AdapterRecycler(showPosts) { partItem: Post -> mainActivity.postClicked(partItem) } }

                    Log.d("POSTS", posts.toString())
                }

            }
        })
    }
    fun fetch10(posts:MutableList<Post>){
        for (i in 0..9){
            showPosts!!.add(posts[i+ showPosts!!.size])
        }
    }/*
    fun onScrollListener(){
        val lastVisibleItemPosition: Int=LinearLayoutManager.findLastVisibleItemPosition()
        var scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                recyclerView?.let { super.onScrollStateChanged(it, newState) }
                val totalItemCount = recyclerView!!.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    Log.d("MyTAG", "Load new list")
                    recycler.removeOnScrollListener(scrollListener)
                }
            }
        }
        recycler.addOnScrollListener(scrollListener)
    }*/
}