package com.dev0jk.depanin.model.data.remote

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.MessageResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class UserRemote {

    val db = Firebase.firestore
    private val storage_referance = FirebaseStorage.getInstance().getReference()


    fun addUser(user: User, userImage: Uri?): LiveData<MessageResult> {

        var mutableLiveData = MutableLiveData<MessageResult>()
        try {


        if (userImage == null){

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                mutableLiveData.value = MessageResult(true, documentReference.id)

            }
            .addOnFailureListener { e ->
                mutableLiveData.value = MessageResult(false, e.message.toString())
            }
        }

            else{

                val ref = storage_referance.child("users/${user.phone+ Date().toString().replace(" ","") }")

            var uploadTask=  ref.putFile(userImage)

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                        mutableLiveData.value = MessageResult(false,it.message.toString())

                    }
                }
                ref.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var downloadUri = task.result
                    user.userImage = downloadUri.toString()

                    var result = db.collection("users").document()
                    val id = result.id
                    result.set(user)



                    mutableLiveData.value = MessageResult(true,id)

                }
            }

            }
        }catch (e :Exception){
            mutableLiveData.value = MessageResult(false,"")
        }
        return mutableLiveData
    }



    fun updateType(phone: String, type: String, userId: String): LiveData<MessageResult> {

        var mutableLiveData = MutableLiveData<MessageResult>()
        try {
            db.collection("users").document(userId).update(mapOf(
                "type" to type,
            )).addOnSuccessListener {
                mutableLiveData.value = MessageResult(true, "")
            }.addOnFailureListener{
                mutableLiveData.value = MessageResult(false, it.message.toString())
            }

        }catch (e :Exception){
            mutableLiveData.value = MessageResult(false, "")
        }
        return mutableLiveData
    }

}