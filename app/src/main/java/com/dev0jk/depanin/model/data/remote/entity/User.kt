package com.dev0jk.depanin.model.data.remote.entity

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("username") var username: String,
    @SerializedName("password") val password: String,
    @SerializedName("address_gov") val address_gov: String?,
    @SerializedName("address_municipale") val address_municipale: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("phone") val phone: Int?,
    @SerializedName("cin") val cin: Int?,
    @SerializedName("niveau") val niveau: String?,
    val isLogin: Boolean?
    )
{


    var users = ArrayList<User>()
}
