package com.example.appmvp.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvp.R
import kotlinx.android.synthetic.main.fragment_post.view.*


class AdapterRecycler(private var postList: MutableList<Post>?, val clickListener: (Post)->Unit) : RecyclerView.Adapter<AdapterRecycler.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_post, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = postList?.size!!


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.postName?.text = postList?.get(position)?.title
        postList?.get(position)?.let { holder.bind(it, clickListener) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postName: TextView? = itemView.findViewById(R.id.title)
        fun bind(part:Post, clickListener: (Post) -> Unit){
            itemView.title.text = part.title
            itemView.showInfo.setOnClickListener{clickListener(part)}

        }

    }
}