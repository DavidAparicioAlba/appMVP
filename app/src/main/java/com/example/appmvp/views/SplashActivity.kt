package com.example.appmvp.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.appmvp.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    var circ:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        circ=circle
        circ?.animate()?.translationX(300.toFloat())?.translationX(-600.toFloat())?.translationX(300.toFloat())?.setDuration(5000)
        Handler().postDelayed({
            startActivity(Intent(this, InitActivity::class.java))
        }, 5000)

    }
}
