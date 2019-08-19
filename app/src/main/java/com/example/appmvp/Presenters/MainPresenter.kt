package com.example.appmvp.Presenters


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvp.Models.Post
import com.example.appmvp.Models.RestPost
import com.example.appmvp.R
import com.example.appmvp.Views.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainPresenter {
        var listRV= MainActivity::listRView
        var postsList=ArrayList<Post>()
        internal fun getCurrentData() {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(RestPost::class.java)
            val call = service.getPostsData()

        }

}

