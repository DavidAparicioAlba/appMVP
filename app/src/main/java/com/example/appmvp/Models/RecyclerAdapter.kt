package com.example.appmvp.Models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.appmvp.R

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {


    var postList : List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.postName.text = postList.get(position).title
    }

    fun setPostsListItems(postList: List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val postName: TextView = itemView.findViewById(R.id.title)


    }
}