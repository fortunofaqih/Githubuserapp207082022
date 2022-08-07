package com.example.githubuserapp1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListGithubAdapter (val listGithub: ArrayList<Github>): RecyclerView.Adapter<ListGithubAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_github, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (username, name, location, repository, company, followers, following, avatars) = listGithub[position]

        holder.apply {
            imgPhoto.setImageResource(avatars)
            tvUserName.text = username
            tvName.text = name

        }



        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listGithub[holder.adapterPosition])
        }

    }


    override fun getItemCount(): Int = listGithub.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
            var tvUserName: TextView = itemView.findViewById(R.id.tv_item_username)
            var tvName: TextView = itemView.findViewById(R.id.tv_item_name)


        }


    interface OnItemClickCallback {
        fun onItemClicked(data: Github)

    }

}
