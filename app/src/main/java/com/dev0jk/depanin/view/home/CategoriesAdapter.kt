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

class CategoriesAdapter(val context: Context, val categories : ArrayList<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {


   inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {

       fun bind(category : Category){
            itemView.findViewById<TextView>(R.id.category).text = category.name

                itemView.findViewById<ImageView>(R.id.img_category).setImageResource(category.icon)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false))
    }


    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(categories.get(position))
    }

}
