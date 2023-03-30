package com.dev0jk.depanin.view.settings

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.dev0jk.depanin.R
import com.dev0jk.depanin.view.SelectLocationActivity
import com.dev0jk.depanin.view.settings.edit.NewTaskSheet


class SettingsAdapter(var context: Context, var settings : ArrayList<String>,private val supportFragmentManager: FragmentManager) :
    RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {

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
                   "Edit Specialty" -> {
                      Toast.makeText(context,"Edit Specialty",Toast.LENGTH_LONG).show()
                   }

                   "Edit Profile" -> {
                      showNewTaskSheet()
                   }

                   "My Address" -> {
                       val intent = Intent(context, SelectLocationActivity::class.java)
                       context.startActivity(intent)
                   }
                   "Language" -> {
                       Toast.makeText(context,"Language",Toast.LENGTH_LONG).show()
                   }
                   "Contact us" -> {
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
