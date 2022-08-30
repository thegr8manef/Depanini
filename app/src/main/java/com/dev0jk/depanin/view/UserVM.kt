package com.dev0jk.depanin.view

import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.repository.UserRepository
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.MessageResult

class UserVM {

    val userRepository = UserRepository()

    fun addUser(user : User) : LiveData<MessageResult> {
        return userRepository.addUser(user)
    }
}