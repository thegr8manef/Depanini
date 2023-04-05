package com.dev0jk.depanin.utils

import android.content.Context
import com.dev0jk.depanin.model.data.remote.entity.User


fun setUser(user: User, context: Context){
    val editor = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).edit()
    editor.putLong("id", user.id!!)
    editor.putString("username", user.username)
    editor.putString("password", user.password)
    editor.putString("address_gov", user.address_gov)
    editor.putString("address_municipale", user.address_municipale)
    editor.putString("image", user.image)
    editor.putString("phone", user.phone.toString())
    editor.putString("cin", user.cin.toString())
    editor.putString("speciality", user.speciality)
    editor.apply()

}

fun getUser(context: Context): User {
    val id = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getLong("id", 0)
    val username = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getString("username", "default_value")
    val password = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getString("password", "default_value")
    val address_gov = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getString("address_gov", "default_value")
    val address_municipale = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getString("address_municipale", "default_value")
    val image = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getString("image", "default_value")
    val phone = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getString("phone", "default_value")
    val cin = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getString("cin", "default_value")
    val speciality = context.applicationContext.getSharedPreferences("list_user", Context.MODE_PRIVATE).getString("speciality", "default_value")
    return User(id!!.toLong(),username!!,password.toString(),address_gov.toString(),address_municipale.toString(),image.toString(),phone!!.toInt(),cin,speciality.toString(),true)
}


/*
fun getSpeciality(): String? {
    return speciality
}

fun setSpeciality(speciality: String?) {
    this.speciality = speciality
}

fun getEmail(): String? {
    return email
}

fun setEmail(email: String?) {
    if (email != null) {
        this.email = email
    }
}

fun getUsername(): String? {
    return username
}

fun setUsername(username: String?) {
    this.username = username
}

fun getPassword(): String? {
    return password
}

fun setPassword(password: String?) {
    this.password = password!!
}

fun getRole(): String? {
    return role
}
fun setRole(role: String?) {
    this.role = role
}*/
