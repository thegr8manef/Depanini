package com.dev0jk.depanin.model.data.remote.entity

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("username")  val username: String,
    @SerializedName("password")  val password: String,
    @SerializedName("address")  val address: String,
    @SerializedName("image")  val image: String,
    @SerializedName("phone")  val phone: Int,
    @SerializedName("cin")  val cin: Int,
    @SerializedName("niveau")  val niveau: String
    )
{


    var users = ArrayList<User>()
}
