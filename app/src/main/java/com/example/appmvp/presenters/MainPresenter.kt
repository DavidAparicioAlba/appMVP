package com.example.appmvp.presenters


import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvp.models.AdapterRecycler
import com.example.appmvp.models.Post
import com.example.appmvp.services.RestPost
import com.example.appmvp.views.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response


class MainPresenter(var mainActivity: MainActivity) {

    var posts:MutableList<Post>? = mutableListOf()
    var showPosts:MutableList<Post>? = mutableListOf()
    lateinit var adaptr: AdapterRecycler
    var isLoading=false
    val numberOfPostsLoaded:Int?=10

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

                    mainListView.layoutManager = LinearLayoutManager(mainActivity)
                    adaptr =
                        response?.body()?.let { AdapterRecycler(showPosts) { partItem: Post -> mainActivity.postClicked(partItem) } }!!
                    mainListView.adapter = adaptr
                    fetch10(posts!!, mainListView)

                    mainListView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            if (dy>0){

                                val visibleItemCount= showPosts!!.size
                                val total = posts!!.size
                                if (!isLoading){
                                    if (visibleItemCount<total){
                                        fetch10(posts!!, mainListView)
                                    }
                                }
                            }



                            super.onScrolled(recyclerView, dx, dy)
                        }
                    })
                    Log.d("SHOWPOSTS", showPosts?.size.toString())
                    Log.d("POSTS", posts?.size.toString())
                }

            }
        })
    }
    fun fetch10(posts:MutableList<Post>, mainListView: RecyclerView){
        isLoading=true
        mainActivity.progressBar.visibility= View.VISIBLE
        for (i in 0..9){
            showPosts!!.add(posts[i+ showPosts!!.size])
        }

        Handler().postDelayed({
            if(::adaptr.isInitialized){
                adaptr.notifyDataSetChanged()
            }else{
                adaptr=AdapterRecycler(showPosts){ partItem: Post -> mainActivity.postClicked(partItem)}
                mainListView.adapter=adaptr
            }
            isLoading=false
            mainActivity.progressBar.visibility= View.GONE
        },4000)
    }
}