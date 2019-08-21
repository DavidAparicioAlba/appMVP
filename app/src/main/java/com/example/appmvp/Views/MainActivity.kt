package com.example.appmvp.views


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmvp.R
import com.example.appmvp.models.AdapterRecycler
import com.example.appmvp.models.Post
import com.example.appmvp.models.RestPost
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RestPost.create().getPostsData().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: retrofit2.Call<List<Post>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "failed response", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<List<Post>>?,
                response: Response<List<Post>>?) {
                    mainListView.layoutManager = LinearLayoutManager( this@MainActivity)
                    mainListView.adapter = response?.body()?.let { AdapterRecycler(it, { partItem : Post -> postClicked(partItem) }) }


            }
        })

    }
    fun postClicked(partItem : Post) {
        Toast.makeText(this, "Clicked: ${partItem.id}", Toast.LENGTH_LONG).show()

        // Launch second activity, pass part ID as string parameter
        val showDetailActivityIntent = Intent(this, DetailsActivity::class.java)
        showDetailActivityIntent.putExtra("title", partItem.title.toString())
        showDetailActivityIntent.putExtra("body", partItem.body.toString())
        startActivity(showDetailActivityIntent)
    }

}

