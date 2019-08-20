package com.example.appmvp.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.appmvp.R

class AdapterRecycler(var postList: List<Post>) : RecyclerView.Adapter<AdapterRecycler.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = postList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.postName?.text = postList[position].title
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postName: TextView? = itemView!!.findViewById(R.id.title)
    }
}