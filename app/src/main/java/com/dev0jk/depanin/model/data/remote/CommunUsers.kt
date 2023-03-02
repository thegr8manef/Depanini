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

