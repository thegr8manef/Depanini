package com.dev0jk.depanin.model.data.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.remote.UserRemote
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.MessageResult

class UserRepository {

    val userRemote = UserRemote()

    fun addUser(user: User, userImage: Uri?) : LiveData<MessageResult>{
        return userRemote.addUser(user, userImage)
    }
}