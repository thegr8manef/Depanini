package com.dev0jk.depanin.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.content.SharedPreferences
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dev0jk.depanin.R
import com.dev0jk.depanin.model.entity.Gouvernement

class GouvernementAdapter (
    val context: Context,
    private val _listOfCities2: ArrayList<Gouvernement>
    ) : RecyclerView.Adapter
    <GouvernementAdapter.ViewHolder>() {
    var oneTap = false
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_gouvernement, parent, false))
        }

        override fun getItemCount(): Int {
            return _listOfCities2.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindView(_listOfCities2[position])
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            @SuppressLint("ResourceAsColor", "SuspiciousIndentation")
            fun bindView(data: Gouvernement) {
                val sharedPref = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)


                /************Send data to the layout*************/

                itemView.findViewById<TextView>(R.id.id).text = data.id
                fun SelectCity(){
                    if (!oneTap) {

                        oneTap=true
                        //Toast.makeText(context, "${data.id}", Toast.LENGTH_SHORT).show()
                        itemView.setBackgroundColor(Color.parseColor("#1E56A0"))
                        itemView.findViewById<TextView>(R.id.id).setTextColor(Color.parseColor("#ffffff"))
                        //Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                        val editor = sharedPref.edit()
                        editor.putString("ChosenCity", data.id)
                        editor.putString("ChosenGouv", data.value)
                        editor.apply()
                    }else{
                        Toast.makeText(context, context.getString(R.string.one_city), Toast.LENGTH_SHORT).show()

                    }
                }


                    itemView.setOnClickListener {
                       SelectCity()
                }






            }
        }
    }