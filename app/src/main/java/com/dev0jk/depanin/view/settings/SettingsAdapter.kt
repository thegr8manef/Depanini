package com.dev0jk.depanin.view.settings

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev0jk.depanin.R
import com.dev0jk.depanin.model.entity.Category
import com.dev0jk.depanin.view.location.LocationActivity

class SettingsAdapter(val context: Context, val settings : ArrayList<String>) :
    RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {


   inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {

       fun bind(setting : String){

            itemView.findViewById<TextView>(R.id.setting_name).text = setting
           itemView.setOnClickListener{
               when(itemView.findViewById<TextView>(R.id.setting_name).text.toString() ){
                   "My Address" -> {
                       val intent = Intent(context, LocationActivity::class.java)
                       context.startActivity(intent)
                   }
               }

           }

       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.setting_item, parent, false))
    }


    override fun getItemCount(): Int {
        return settings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(settings.get(position))
    }

}
