package com.example.appmvp.presenters

// no tendrian que estar
/*import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvp.views.AdapterRecycler
*/

//
import com.example.appmvp.models.Post
import com.example.appmvp.services.RestPost
import com.example.appmvp.services.repo.Repository
// tampoco
import com.example.appmvp.views.MainView
import retrofit2.Callback
import retrofit2.Response
//

class MainPresenter {

    private var view: MainView? = null
    var posts:MutableList<Post>? = mutableListOf()
    var showPosts:MutableList<Post>? = mutableListOf()
   // lateinit var adaptr: AdapterRecycler
    var isLoading=false
    var repo:Repository?=null

    fun setViewContract(view: MainView) {
        this.view = view
    }


    fun onLoad(){
        Repository("Retrofit", "get").fetch(object : Callback<MutableList<Post>> {
            override fun onFailure(
                call: retrofit2.Call<MutableList<Post>>?,
                t: Throwable?
            ) {
            }
            override fun onResponse(
                call: retrofit2.Call<MutableList<Post>>?,
                response: Response<MutableList<Post>>?
            ) {
                posts=response?.body()
            }
        })
        RestPost.create().getPostsData().enqueue(object : Callback<MutableList<Post>> {
            override fun onFailure(call: retrofit2.Call<MutableList<Post>>?, t: Throwable?) {
                //Toast.makeText(mainActivity, "failed response", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(
                call: retrofit2.Call<MutableList<Post>>?,
                response: Response<MutableList<Post>>?
            ) {
                //mainView?.displayData(response?.body())
                if (showPosts!!.size < posts!!.size) {
                    /*
                    mainListView.layoutManager = LinearLayoutManager(mainActivity)
                    adaptr =
                        response?.body()?.let {
                            AdapterRecycler(showPosts) { partItem: Post ->
                                mainActivity.postClicked(
                                    partItem
                                )
                            }
                        }!!
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
                }*/
                }//
            }
        })
    }
    fun fetch10(posts:MutableList<Post>, mainListView: RecyclerView){
        isLoading=true
        mainActivity.progressBar.visibility= View.VISIBLE
        val sizePosts=showPosts!!.size
        for (i in 0..9){
            showPosts!!.add(posts[i+ sizePosts])
        }
        Handler().postDelayed({
            if(::adaptr.isInitialized){
                adaptr.notifyDataSetChanged()
            }else{
                adaptr= AdapterRecycler(showPosts) { partItem: Post ->
                    mainActivity.postClicked(partItem)
                }
                mainListView.adapter=adaptr
            }
            isLoading=false
            mainActivity.progressBar.visibility= View.GONE
        },4000)
    }

}