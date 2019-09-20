package com.example.appmvp.presenters



import android.util.Log
import com.example.appmvp.models.Post
import com.example.appmvp.services.repo.Repository
import com.example.appmvp.views.Contract
import retrofit2.Callback
import retrofit2.Response


class MainPresenter(view: Contract.MainView): Contract.Presenter {

    private var view: Contract.MainView? = view
    var posts:MutableList<Post>? = mutableListOf()
    var showPosts:MutableList<Post>? = mutableListOf()
    var newList:List<Post>? = listOf()
    var isLoading=false

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
                newList=posts?.toList()
                fetch10(newList?.toMutableList())
                view?.setAdapter(showPosts)
                view?.setScrollListener { fetch10(newList?.toMutableList()) }
            }
        })
    }
    fun search(word: String){

        showPosts?.clear()
        newList = posts?.filter { post -> post.title?.contains(word, true) ?: false }
        Log.d("size", newList?.size.toString())
        fetch10(newList?.toMutableList())
        view?.handlePosts(showPosts)

    }

    fun fetch10(posts: MutableList<Post>?){
        val visibleItemCount= showPosts?.size
        val total = posts?.size
        if (!isLoading) {
            if (total != null && visibleItemCount != null) {
                if (visibleItemCount + 9 < total) {
                    for (i in 0..9) {
                        posts.get(i + visibleItemCount).let { showPosts?.add(it) }
                    }

                    view?.handlePosts(showPosts)
                }
            }
        }
        isLoading=true
    }
}