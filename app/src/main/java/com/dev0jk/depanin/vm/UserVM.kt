package com.dev0jk.depanin.vm

import android.net.Uri
import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.repository.UserRepository
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.utils.MessageResult

class UserVM {

    val userRepository = UserRepository()

    fun addUser(user: com.dev0jk.depanin.model.data.remote.entity.User, userImage: Uri?) : LiveData<MessageResult> {
        return userRepository.addUser(user, userImage)
    }

    fun updateType(phone: String, type: String, userId: String): LiveData<MessageResult> {
        return userRepository.updateType(phone, type, userId)
    }
    fun updateLocation(userId : String,location: Location) : LiveData<MessageResult>{
        return userRepository.updateLocation(userId, location)
    }
    fun signUpClient(
        username:String,
        password:String,
        address_gov:String,
        address_municipale:String,
        image:String,
        phone:Int) : LiveData<com.dev0jk.depanin.model.data.remote.entity.User>{
        return userRepository.signUpClient(username, password,address_gov,address_municipale,image,phone)
    }
    fun getAllClient() : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return userRepository.getAllClient()
    }
    fun getData(phone:Int) : LiveData<MessageResult>{
        return userRepository.getData(phone)
    }

    fun authentifacteUsers(username : String,password:String) : LiveData<com.dev0jk.depanin.model.data.remote.entity.User>{
        return userRepository.authentifacteUsers(username ,password)
    }

    suspend fun updateUser(id: Long, username: String?, password: String?) : LiveData<ResponseUserModel>{
        return userRepository.updateUser(id, username!!,password!!)
    }

}