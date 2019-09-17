package com.example.appmvp.views


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvp.R
import com.example.appmvp.models.Post
import com.example.appmvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), contract.MainView {

    var adaptr:AdapterRecycler?=null
    val mainPresenter = MainPresenter(this)
    var scrollOverall:Int=0

    override fun handlePosts(posts: MutableList<Post>?) {
        showProgressBar()
        Handler().postDelayed({
            if(adaptr!=null){
                adaptr?.notifyDataSetChanged()
            }else{
                adaptr= AdapterRecycler(posts) { partItem: Post ->
                    this.postClicked(partItem)
                }
                mainListView.adapter=adaptr
            }
            mainPresenter.isLoading=false
            hideProgressBar()
        },4000)

    }

    override fun setAdapter(posts: MutableList<Post>?) {
        mainListView.layoutManager = LinearLayoutManager(this)
        adaptr =
            posts?.let {
                AdapterRecycler(posts) { partItem: Post ->
                    this.postClicked(
                        partItem
                    )
                }
            }!!
        mainListView.adapter = adaptr
    }

    override fun setScrollListener(funct: (posts:MutableList<Post>?)->Unit) {
        /*mainListView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                scrollOverall+=dy
                funct(dy)
                Log.d("recyclerView", scrollOverall.toString())
                super.onScrolled(recyclerView, dx, dy)
            }
        })*/
        mainListView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)) {
                    mainPresenter.isLoading=false
                    Handler().postDelayed({
                            funct(mainPresenter.posts)
                        }, 4000)

                    Log.d("showposts", mainPresenter.showPosts?.size.toString())
                }
            }
        })
    }

    override fun showProgressBar() {
        progressBar.visibility=View.VISIBLE
        Log.d("progressBar", "visible")
    }

    override fun hideProgressBar() {
        progressBar.visibility= View.GONE
        Log.d("progressBar", "gone")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  mainPresenter.setViewContract(this)
        mainPresenter.onInit()
    }

    fun postClicked(partItem : Post) {
        Toast.makeText(this, "Clicked: ${partItem.id}", Toast.LENGTH_LONG).show()
        // Launch second activity, pass part ID as string parameter
        val showDetailActivityIntent = Intent(this, DetailsActivity::class.java)
        showDetailActivityIntent.putExtra("title", partItem.title.toString())
        showDetailActivityIntent.putExtra("body", partItem.body.toString())
        startActivity(showDetailActivityIntent)
        mainPresenter.onDestroy()
    }
}