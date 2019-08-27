package com.example.appmvp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmvp.R
import com.example.appmvp.presenters.InitPresenter
import kotlinx.android.synthetic.main.activity_init.*

class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        InitPresenter().onLoadImage(initImageView)
    }

    fun startActivity(view: View){
        startActivity(Intent(this, MainActivity::class.java))

        //start new activity
    }
}
