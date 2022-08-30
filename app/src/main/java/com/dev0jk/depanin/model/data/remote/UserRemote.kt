package com.dev0jk.depanin.model.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.MessageResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRemote {

    val db = Firebase.firestore

    fun addUser(user: User): LiveData<MessageResult> {

        var mutableLiveData = MutableLiveData<MessageResult>()
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                mutableLiveData.value = MessageResult(true, documentReference.id)

            }
            .addOnFailureListener { e ->
                mutableLiveData.value = MessageResult(false, e.message.toString())
            }
        return mutableLiveData
    }

}