package com.dev0jk.depanin.vm

import android.net.Uri
import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.repository.UserRepository
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.MessageResult

class UserVM {

    val userRepository = UserRepository()

    fun addUser(user: User, userImage: Uri?) : LiveData<MessageResult> {
        return userRepository.addUser(user, userImage)
    }

    fun updateType(phone: String, type: String, userId: String): LiveData<MessageResult> {
        return userRepository.updateType(phone, type, userId)
    }

}