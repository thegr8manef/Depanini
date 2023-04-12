package com.dev0jk.depanin.model.data.repository

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.remote.UserRemote
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.model.entity.UserImageFb
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.utils.MessageResult

class UserRepository {

    val userRemote = UserRemote()

    fun addUser(user: com.dev0jk.depanin.model.data.remote.entity.User, userImage: Uri?) : LiveData<MessageResult>{
        return userRemote.addUser(user, userImage)
    }

    fun updateType(phone: String, type: String, userId: String): LiveData<MessageResult> {
        return userRemote.updateType(phone, type, userId)
    }
    fun updateLocation(userId : String,location: Location) : LiveData<MessageResult>{
        return userRemote.updateLocationUser(userId, location)
    }
    fun signUpClient(username:String,password:String,address_gov:String,address_municipale:String,image:String,phone:Int) : LiveData<com.dev0jk.depanin.model.data.remote.entity.User>{
        return userRemote.signUpClient(username, password,address_gov,address_municipale,image,phone)
    }

    fun authentifacteUsers(username : String,password:String) : LiveData<com.dev0jk.depanin.model.data.remote.entity.User>{
        return userRemote.authentificateUsers(username ,password)
    }

    fun getData(phone:Int) : LiveData<MessageResult>{
        return userRemote.getDataUser(phone)
    }
    fun getAllClient() : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return userRemote.getAllClient()
    }

    suspend fun updateUser(id : Long, username: String?, password: String?) : LiveData<ResponseUserModel>{
        return userRemote.updateUser(id, username!!,password!!)
    }
}