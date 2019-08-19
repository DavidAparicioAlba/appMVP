package com.example.appmvp.Views


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvp.Models.RecyclerAdapter
import com.example.appmvp.Models.RestPost
import com.example.appmvp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    var listRView: RecyclerView? = null
    var listPosts = ArrayList<Post>()

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.mainListView)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter



        //val restPost = RestPost.create().getPostsData()

        /*restPost.enqueue(object : Callback<List<Post>>() {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.body() != null)
                    recyclerAdapter.setPostsListItems(response.body())
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
            }
        })*/

        RestPost.create().getPostsData().enqueue(object: Callback<List<com.example.appmvp.Models.Post>>{
            override fun onFailure(call: Call<List<com.example.appmvp.Models.Post>>?, t: Throwable?) {
            }

            override fun onResponse(
                call: Call<List<com.example.appmvp.Models.Post>>?,
                response: Response<List<com.example.appmvp.Models.Post>>?
            ) {
                response?.body()?.let {
                    recyclerAdapter.setPostsListItems(response.body())}
            }
        })

    }


}

