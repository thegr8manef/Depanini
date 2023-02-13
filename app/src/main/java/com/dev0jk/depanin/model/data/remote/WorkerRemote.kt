package com.dev0jk.depanin.model.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.MessageResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class WorkerRemote {

    val db = Firebase.firestore



    fun getRecommendedWorker(location : String): MutableLiveData<ArrayList<User>?> {

        var mutableLiveData = MutableLiveData<ArrayList<User>?>()

        db.collection("users")
            .whereEqualTo("address",location)
            .whereEqualTo("type","worker")
            .get()
            .addOnSuccessListener {

                if (!it.documents.isEmpty()){
                    var recommended = ArrayList<User>()


                    for (item in it.documents) {


                        recommended.add(item.toObject(User::class.java)!!)
                    }
                    mutableLiveData.value = recommended

                }
                else{
                    mutableLiveData.value = null

                }


            }
            .addOnFailureListener { exception ->
                mutableLiveData.value = null
            }


        return mutableLiveData
    }

    fun CreateSpecialtyOfWorker(userID: String, specialty: String): LiveData<MessageResult> {

        var mutableLiveData = MutableLiveData<MessageResult>()
        try {
            db.collection("users").document(userID).update(mapOf(
                "specialty" to specialty,
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