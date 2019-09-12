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
import android.util.Log
import com.example.appmvp.models.Post
import com.example.appmvp.services.repo.Repository
// tampoco
import com.example.appmvp.views.contract
import retrofit2.Callback
import retrofit2.Response
//

class MainPresenter(view: contract.MainView): contract.Presenter {

    private var view: contract.MainView? = view
    var posts:MutableList<Post>? = mutableListOf()
    var showPosts:MutableList<Post>? = mutableListOf()
    var isLoading=false

   /* fun setViewContract(view: contract.MainView) {
        this.view = view
    }*/

    override fun onDestroy() {}

    override fun onInit(){
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
                fetch10(posts)

                view?.setAdapter(showPosts)
                view?.setScrollListener { checkPos(it) }
            }
        })
        /*
                if (showPosts!!.size < posts!!.size) {
                    fetch10(response?.body()).let {  }
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
                }
                }//
            }
        })*/
    }
    fun checkPos(dy: Int){
        if (dy>0){
            val visibleItemCount= showPosts?.size
            val total = posts?.size
            if (!isLoading){
                if (total!=null&&visibleItemCount!=null){
                    if (visibleItemCount<total){

                        fetch10(posts)

                    }
                }
            }
            Log.d("SHOWPOSTS", showPosts?.size.toString())
        }
    }
    fun fetch10(posts: MutableList<Post>?){
        isLoading=true

        val sizePosts=showPosts!!.size
        for (i in 0..9){
            posts?.get(i+ sizePosts)?.let { showPosts?.add(it) }
        }
        view?.setAdapter(showPosts)
        view?.handlePosts(showPosts)

        /*Handler().postDelayed({
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
        },4000)*/
    }

}