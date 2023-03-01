package com.dev0jk.depanin.model.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev0jk.depanin.model.data.api.ApiClientInterface
import com.dev0jk.depanin.model.data.service.RetrofitHelper
import com.dev0jk.depanin.model.data.service.ServiceUserBuilder
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.modelWorker.RequestUserModel
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.model.entity.modelWorker.UserModel
import com.dev0jk.depanin.utils.MessageResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkerRemote {

    val db = Firebase.firestore


    fun getRecommendedWorker(location: String): MutableLiveData<ArrayList<User>?> {

        var mutableLiveData = MutableLiveData<ArrayList<User>?>()
        db.collection("users")
            .whereEqualTo("address.municipale", location)
            .whereEqualTo("type", "worker")
            .get()
            .addOnSuccessListener {
                if (!it.documents.isEmpty()) {
                    var recommended = ArrayList<User>()

                    for (item in it.documents) {


                        recommended.add(item.toObject(User::class.java)!!)
                    }
                    mutableLiveData.value = recommended

                } else {
                    Log.v("================>firebase2", "$location")
                    mutableLiveData.value = null

                }


            }
            .addOnFailureListener {
                mutableLiveData.value = null
            }


        return mutableLiveData
    }

    fun createSpecialtyOfWorker(userID: String, specialty: String): LiveData<MessageResult> {

        var mutableLiveData = MutableLiveData<MessageResult>()
        try {
            db.collection("users").document(userID).update(
                mapOf(
                    "specialty" to specialty,
                )
            ).addOnSuccessListener {
                mutableLiveData.value = MessageResult(true, "")
            }.addOnFailureListener {
                mutableLiveData.value = MessageResult(false, it.message.toString())
            }

        } catch (e: Exception) {
            mutableLiveData.value = MessageResult(false, "")
        }
        return mutableLiveData
    }

    fun updateLocation(userId: String, location: Location): LiveData<MessageResult> {
        return updateLocation(userId, location)
    }

    fun getDataWorker(phone: Int): LiveData<MessageResult> {
        return getData(phone)
    }

    fun signUpWorker(
        username: String,
        password: String,
        address: String,
        phone: Int,
        cin: Int,
        niveau: String
    ): LiveData<ResponseUserModel> {
        val requestUserModel = RequestUserModel(username, password, address, phone, cin, niveau)

        var mutableLiveData = MutableLiveData<ResponseUserModel>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")

        ServiceUserBuilder.apiWorker.sendReqWorker(requestUserModel).enqueue(object :
            Callback<ResponseUserModel> {

            override fun onResponse(

                call: Call<ResponseUserModel>,
                response: Response<ResponseUserModel>
            ) {
                Log.println(Log.ASSERT, "", response.message())
                if (response.isSuccessful) {

                    //mutableLiveData.value = response.body()?.message()
                    Log.println(
                        Log.ASSERT,
                        "======================>",
                        mutableLiveData.value.toString()
                    )
//                   response.body()?.results?.forEach{
//                       Log.println(Log.ASSERT, "", it.toString())
//                   }
                }
            }

            override fun onFailure(call: Call<ResponseUserModel>, t: Throwable) {
                Log.println(Log.ASSERT, "======================>", t.message.toString())

            }


        })

        return mutableLiveData
    }

    fun getAllWorker(): LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>> {
        var readAllData = MutableLiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>()

/*
        try {
            val service = RetrofitHelper.getInstance().create(ApiClientInterface::class.java)
            MainScope().launch {
                var response = service.getDataWorker()
                //Log.println(Log.ASSERT, "============>Response", response.body()?.data.toString())
                val errorCode = response.code()
                if (errorCode == 500) {
                    Log.println(Log.ASSERT, "============>Code500", "phone is already exist")
                    Log.println(Log.ASSERT, "============>ResponseApi", response.body().toString())

                }
                if (errorCode==200){
                    Log.println(Log.ASSERT, "============>Code200", "phone is already exist")
                    Log.println(Log.ASSERT, "============>ResponseApi", response.body().toString())

                }

            }

        } catch (e: Exception) {

        }


        return readAllData*/
        val apiService = RetrofitHelper.getInstance().create(ApiClientInterface::class.java)

        val call = apiService.getAllUsers()

        call.enqueue(object : Callback<List<com.dev0jk.depanin.model.data.remote.entity.User>> {
            override fun onResponse(call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>, response: Response<List<com.dev0jk.depanin.model.data.remote.entity.User>>) {
                if (response.isSuccessful) {
                    val usersResponse = response.body()
                    Log.println(Log.ASSERT, "============>ResponseApi", "1")

                    Log.println(Log.ASSERT, "============>ResponseApi", usersResponse.toString())
                    // Do something with the list of users
                } else {
                    Log.println(Log.ASSERT, "============>ResponseApi", "2")
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>, t: Throwable) {
                // Handle network error
                Log.println(Log.ASSERT, "============>ResponseApi", "3")
            }

        })
        return readAllData
    }



}

