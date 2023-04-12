package com.dev0jk.depanin.view.settings.client

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.dev0jk.depanin.R
import com.dev0jk.depanin.view.SelectLocationActivity
import com.dev0jk.depanin.view.settings.edit.EditLocationActivity
import com.dev0jk.depanin.view.settings.edit.EditProfileActivity
import com.dev0jk.depanin.view.settings.edit.NewTaskSheet


class ClientSettingsAdapter(var context: Context, var settings : ArrayList<String>, private val supportFragmentManager: FragmentManager) :
    RecyclerView.Adapter<ClientSettingsAdapter.ViewHolder>() {

   //     val  myAdapter = SettingsAdapter(context,settings, supportFragmentManager)


    fun showNewTaskSheet() {
        val newTaskSheet = NewTaskSheet()
        newTaskSheet.show(supportFragmentManager, "newTaskTag")
    }
    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {

       fun bind(setting : String){

            itemView.findViewById<TextView>(R.id.setting_name).text = setting
           itemView.setOnClickListener{
               when(itemView.findViewById<TextView>(R.id.setting_name).text.toString() ){
                   "Edit Profile" -> {
                       val intent = Intent(context, EditProfileActivity::class.java)
                       context.startActivity(intent)
                   }

                   "My Address" -> {
                       val intent = Intent(context, EditLocationActivity::class.java)
                       context.startActivity(intent)
                   }
                   "Language" -> {
                       showNewTaskSheet()
                   }
                   "Contact Us" -> {
                       Toast.makeText(context,"Contact us",Toast.LENGTH_LONG).show()
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
