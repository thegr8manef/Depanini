package com.dev0jk.depanin.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev0jk.depanin.R
import com.dev0jk.depanin.model.entity.Category
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.ScaleImageView

class RecommendedAdapter(val context: Context, val recommends : ArrayList<User>) :
    RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {


   inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {

       fun bind(user : User){
           Glide.with(context)
               .load(user.userImage)
               .into( itemView.findViewById<ImageView>(R.id.user_image))
               itemView.findViewById<TextView>(R.id.worker_name).text = user.name +" " +user.lastName


       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recommended_item, parent, false))
    }


    override fun getItemCount(): Int {
        return recommends.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(recommends.get(position))
    }

}
