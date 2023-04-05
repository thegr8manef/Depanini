package com.dev0jk.depanin.view.search

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev0jk.depanin.R
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.view.SelectLocationActivity
import com.dev0jk.depanin.view.home.WorkerDetailedActivity

class SearchAdapter(val context: Context, val recommends: ArrayList<User>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


   inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {

       fun bind(user : com.dev0jk.depanin.model.data.remote.entity.User){
           Glide.with(context)
               .load(user.image)
               .into( itemView.findViewById<ImageView>(R.id.user_image))
               itemView.findViewById<TextView>(R.id.worker_name).text = user.username
               itemView.findViewById<TextView>(R.id.worker_gov).text = user.address_gov
               itemView.findViewById<TextView>(R.id.worker_municipale).text = user.address_municipale
               itemView.findViewById<TextView>(R.id.worker_job).text = user.speciality

           itemView.setOnClickListener {
               val intent = Intent(context, WorkerDetailedActivity::class.java).apply {
                   putExtra("username_worker",user.username)
                   putExtra("address_gov_worker",user.address_gov)
                   putExtra("address_municipale_worker",user.address_municipale)
                   putExtra("image_worker",user.image)
                   putExtra("phone_worker",user.phone)
                   Log.println(Log.ASSERT,"worker phone",user.phone.toString())
                   putExtra("speciality_worker",user.speciality)
               }
               context.startActivity(intent)
           }


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
