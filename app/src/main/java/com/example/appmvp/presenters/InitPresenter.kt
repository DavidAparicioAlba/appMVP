package com.example.appmvp.presenters

import android.widget.ImageView
import com.squareup.picasso.Picasso

class InitPresenter (){
    fun onLoadImage(initImageView:ImageView){
        Picasso.get().load("https://picsum.photos/500").into(initImageView)
    }
}

