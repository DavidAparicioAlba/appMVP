package com.example.appmvp.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appmvp.R

class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
    }

    fun startActivity(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        //start new activity
    }
}
