package com.dev0jk.depanin.view.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dev0jk.depanin.R
import com.dev0jk.depanin.model.entity.Category

class CategoriesAdapter(
    val context: Context,
    val categories: ArrayList<Category>,
   val homeFragment: HomeFragment
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {


   inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {

       fun bind(category : Category){
            itemView.findViewById<TextView>(R.id.category).text = category.name

                itemView.findViewById<ImageView>(R.id.img_category).setImageResource(category.icon)
           itemView.setOnClickListener {
               homeFragment.filtre(category.name)
            /*   when (itemView.findViewById<TextView>(R.id.category).text.toString()) {
                   "All" -> {
                       Toast.makeText(context,"All", Toast.LENGTH_LONG).show()
                       val intent = Intent(context, HomeActivity::class.java).apply {
                           val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
                           editor.putString("category.name", category.name)
                           editor.apply()
                       }
                       context.startActivity(intent)

                   }
                   "Electricity" -> {
                       Toast.makeText(context,"Electricity", Toast.LENGTH_LONG).show()
                       val intent = Intent(context, HomeActivity::class.java).apply {
                           val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
                           editor.putString("category.name", category.name)
                           editor.apply()
                       }
                       context.startActivity(intent)
                   }
                   "Plumbing" -> {
                       Toast.makeText(context,"Plumbing", Toast.LENGTH_LONG).show()
                       val intent = Intent(context, HomeActivity::class.java).apply {
                           val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
                           editor.putString("category.name", category.name)
                           editor.apply()
                       }
                       context.startActivity(intent)
                   }
                   "Painting" -> {
                       Toast.makeText(context,"Painting", Toast.LENGTH_LONG).show()
                       val intent = Intent(context, HomeActivity::class.java).apply {
                           val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
                           editor.putString("category.name", category.name)
                           editor.apply()
                       }
                       context.startActivity(intent)
                   }
                   "Gardener" -> {
                       Toast.makeText(context,"Gardener", Toast.LENGTH_LONG).show()
                       val intent = Intent(context, HomeActivity::class.java).apply {
                           val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
                           editor.putString("category.name", category.name)
                           editor.apply()
                       }
                       context.startActivity(intent)
                   }
                   "Security" -> {
                       Toast.makeText(context,"Security", Toast.LENGTH_LONG).show()
                       val intent = Intent(context, HomeActivity::class.java).apply {
                           val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
                           editor.putString("category.name", category.name)
                           editor.apply()
                       }
                       context.startActivity(intent)
                   }
                   "Masonry" -> {
                       Toast.makeText(context,"Masonry", Toast.LENGTH_LONG).show()
                       val intent = Intent(context, HomeActivity::class.java).apply {
                           val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
                           editor.putString("category.name", category.name)
                           editor.apply()
                       }
                       context.startActivity(intent)
                   }
                   "Carpenter" -> {
                       Toast.makeText(context,"Carpenter", Toast.LENGTH_LONG).show()
                       val intent = Intent(context, HomeActivity::class.java).apply {
                           val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
                           editor.putString("category.name", category.name)
                           editor.apply()
                       }
                       context.startActivity(intent)
                   }
               }*/
           }
/*               Log.println(Log.ASSERT,"category clicked",category.name)
               val editor = context.getSharedPreferences("category_name", Context.MODE_PRIVATE).edit()
               editor.putString("category.name", category.name)
               editor.apply()
           }*/
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
