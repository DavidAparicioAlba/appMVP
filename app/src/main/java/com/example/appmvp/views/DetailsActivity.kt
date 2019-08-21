package com.example.appmvp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmvp.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val title:String = intent.getStringExtra("title")
        val body:String = intent.getStringExtra("body")
        textViewBody.setText(body)
        textViewTitle.setText(title)

    }

    fun backOnClick(v: View){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
