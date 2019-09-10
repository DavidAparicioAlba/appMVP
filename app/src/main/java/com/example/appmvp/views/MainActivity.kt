package com.example.appmvp.views


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appmvp.R
import com.example.appmvp.models.Post
import com.example.appmvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {
    override fun setAdapter(posts: MutableList<Post>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainPresenter = MainPresenter()
        mainPresenter.setViewContract(this)
        mainPresenter.onLoad()

    }
    fun postClicked(partItem : Post) {
        //Toast.makeText(this, "Clicked: ${partItem.id}", Toast.LENGTH_LONG).show()

        // Launch second activity, pass part ID as string parameter
        val showDetailActivityIntent = Intent(this, DetailsActivity::class.java)
        showDetailActivityIntent.putExtra("title", partItem.title.toString())
        showDetailActivityIntent.putExtra("body", partItem.body.toString())
        startActivity(showDetailActivityIntent)
    }


}

