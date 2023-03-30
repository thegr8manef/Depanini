package com.dev0jk.depanin.model.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev0jk.depanin.model.data.api.ApiClientInterface
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.model.data.service.RetrofitHelper
import com.dev0jk.depanin.model.data.service.ServiceUserBuilder
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.modelWorker.RequestUserModel
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.model.entity.modelWorker.UserModel
import com.dev0jk.depanin.utils.MessageResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun updateLocation (userId : String, location: Location) : LiveData<MessageResult>{
    val db = Firebase.firestore

    var mutableLiveData = MutableLiveData<MessageResult>()
    try {
        db.collection("users").document(userId).update(mapOf(
            "address" to location,
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
fun getData(phone : Int):  LiveData<MessageResult> {
    var readAllData = MutableLiveData<MessageResult>()


    try {
        val service = RetrofitHelper.getInstance().create(ApiClientInterface::class.java)
        MainScope().launch {


            var response = service.getCurrency(phone)
            //Log.println(Log.ASSERT, "============>Response", response.body()?.data.toString())
            val errorCode = response.code()
            if (errorCode == 500) {
                Log.println(Log.ASSERT, "============>Code500", "phone is already exist")
                Log.println(Log.ASSERT, "============>ResponseApi", response.body().toString())
                readAllData.value?.statu = response.body().toString().toBoolean()
                Log.println(Log.ASSERT, "============>statu", response.body().toString().toBoolean().toString())

            }
            if (errorCode==200){
                var  messageResult = MessageResult(response.body().toString().toBoolean()," ")
//                Log.println(Log.ASSERT, "============>Code200", "phone is already exist")
//                Log.println(Log.ASSERT, "============>ResponseApi", response.body().toString())
                readAllData.value= messageResult
              //  Log.println(Log.ASSERT, "============>statu", response.body().toString().toBoolean().toString())


            }

        }

    } catch (e: Exception) {

    }


    return readAllData

}
suspend fun updateUser(id : Long,
                       username: String,
                       password : String
): LiveData<ResponseUserModel> {
    val requestUserModel = RequestUserModel(username, password,null,null,null,null)
    var readAllData = MutableLiveData<ResponseUserModel>()

    //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")
    val code200=true
    try {


        ServiceUserBuilder.apiUser.UpdateWorker(id, requestUserModel).enqueue(object :
            Callback<ResponseUserModel> {
            override fun onResponse(

                call: Call<ResponseUserModel>,
                response: Response<ResponseUserModel>
            ) {
                Log.println(Log.ASSERT, "body", response.body().toString())

                var messageResult: ResponseUserModel = if (response.code() == 200) {
                    Log.println(Log.ASSERT, "header", "code 200")
                    ResponseUserModel(response.headers()["authorization"].toString(), true)

                } else {
                    Log.println(Log.ASSERT, "header", "code !=  200")
                    ResponseUserModel(response.headers()["authorization"].toString(), false)

                }
                readAllData.value = messageResult
                //mutableLiveData.value = response.body()
//                   response.body()?.results?.forEach{
//                       Log.println(Log.ASSERT, "", it.toString())
//                   }
            }


            override fun onFailure(call: Call<ResponseUserModel>, t: Throwable) {
                Log.println(Log.ASSERT, "header", "code 404")

/*
            var  messageResult : ResponseUserModel = if (code200){
                ResponseUserModel("null",true)

            }else{
                ResponseUserModel("null",false)
            }
            readAllData.value= messageResult
*/

            }


        })
    }catch (e: Exception){}

    return readAllData
}
