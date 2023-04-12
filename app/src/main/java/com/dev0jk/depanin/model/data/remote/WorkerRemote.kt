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
import com.dev0jk.depanin.utils.MessageResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkerRemote {
    val db = Firebase.firestore


     fun getRecommendedWorker(
        address_municipale: String,
        address_gov: String
    ): LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>> {
/*
        var readAllData = MutableLiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>?>()
        try{
        val service = RetrofitHelper.getInstance().create(ApiClientInterface::class.java)
        MainScope().launch {

            var response = service.getRecommendedWorker(address_municipale, address_gov)
            //Log.println(Log.ASSERT, "============>Response", response.body()?.data.toString())
            if (response.isSuccessful) {

                        val messageResult = response.body()


                readAllData.value = messageResult
            }

        }

        } catch (e: Exception) {

        }


        return readAllData*/

        var readAllData = MutableLiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")

        ServiceUserBuilder.apiWorker.getRecommendedWorker(address_municipale,address_gov).enqueue(object :
            Callback<List<com.dev0jk.depanin.model.data.remote.entity.User>> {

            override fun onResponse(

                call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>,
                response: Response<List<com.dev0jk.depanin.model.data.remote.entity.User>>
            ) {
                Log.println(Log.ASSERT, "", response.message())
                if (response.isSuccessful) {
                    Log.println(Log.ASSERT, "", response.body().toString())

                        if (response.body() != null) {
                            readAllData.value = response.body()

                        }
                }

            }

            override fun onFailure(call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>, t: Throwable) {
                Log.println(Log.ASSERT, "======================>2", t.message.toString())

            }



        })

        return readAllData
    }

    fun filterbySpeciality(
        speciality: String
    ): LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>> {

        var readAllData = MutableLiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")

        ServiceUserBuilder.apiWorker.filterbySpeciality(speciality).enqueue(object :
            Callback<List<com.dev0jk.depanin.model.data.remote.entity.User>> {

            override fun onResponse(

                call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>,
                response: Response<List<com.dev0jk.depanin.model.data.remote.entity.User>>
            ) {
                Log.println(Log.ASSERT, "", response.message())
                if (response.isSuccessful) {
                    Log.println(Log.ASSERT, "", response.body().toString())

                    if (response.body() != null) {
                        readAllData.value = response.body()

                    }
                }

            }

            override fun onFailure(call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>, t: Throwable) {
                Log.println(Log.ASSERT, "======================>2", t.message.toString())

            }



        })

        return readAllData
    }

    fun searchByUsername(
        username: String
    ): LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>> {

        var readAllData = MutableLiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")

        ServiceUserBuilder.apiWorker.searchByUsername(username).enqueue(object :
            Callback<List<com.dev0jk.depanin.model.data.remote.entity.User>> {

            override fun onResponse(

                call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>,
                response: Response<List<com.dev0jk.depanin.model.data.remote.entity.User>>
            ) {
                Log.println(Log.ASSERT, "", response.message())
                if (response.isSuccessful) {
                    Log.println(Log.ASSERT, "", response.body().toString())

                    if (response.body() != null) {
                        readAllData.value = response.body()

                    }
                }

            }

            override fun onFailure(call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>, t: Throwable) {
                Log.println(Log.ASSERT, "======================>2", t.message.toString())

            }



        })

        return readAllData
    }

    fun findAllFavorites(
        id_client: Long
    ): LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>> {

        var readAllData = MutableLiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>()

        ServiceUserBuilder.apiWorker.findAllFavorites(id_client).enqueue(object :
            Callback<List<com.dev0jk.depanin.model.data.remote.entity.User>> {

            override fun onResponse(

                call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>,
                response: Response<List<com.dev0jk.depanin.model.data.remote.entity.User>>
            ) {
                Log.println(Log.ASSERT, "listAllFavorites", response.message())
                if (response.isSuccessful) {
                    Log.println(Log.ASSERT, "listAllFavorites", response.body().toString())

                    if (response.body() != null) {
                        readAllData.value = response.body()

                    }
                }

            }

            override fun onFailure(call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>, t: Throwable) {
                Log.println(Log.ASSERT, "======================>2", t.message.toString())

            }



        })

        return readAllData
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
        username:String,password:String,address_gov:String,address_municipale:String,image:String,phone:Int,cin: String, speciality: String
    ): LiveData<com.dev0jk.depanin.model.data.remote.entity.User> {
        val user = com.dev0jk.depanin.model.data.remote.entity.User(null,username, password, address_gov,address_municipale, image,phone, cin, speciality,false)

        var readAllData = MutableLiveData<com.dev0jk.depanin.model.data.remote.entity.User>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")

        ServiceUserBuilder.apiWorker.sendReqWorker(user).enqueue(object :
            Callback<com.dev0jk.depanin.model.data.remote.entity.User> {

            override fun onResponse(

                call: Call<com.dev0jk.depanin.model.data.remote.entity.User>,
                response: Response<com.dev0jk.depanin.model.data.remote.entity.User>
            ) {
                Log.println(Log.ASSERT, "", response.message())
                if (response.isSuccessful) {
                    val messageResult: com.dev0jk.depanin.model.data.remote.entity.User =
                        if (response.body() != null) {
                            com.dev0jk.depanin.model.data.remote.entity.User(
                                response.body()!!.id!!.toLong(),
                                response.body()!!.username,
                                response.body()!!.password,
                                response.body()!!.address_gov,
                                response.body()!!.address_municipale,
                                response.body()!!.image,
                                response.body()!!.phone,
                                response.body()!!.cin,
                                response.body()!!.speciality,
                                true
                            )
/*                        val editor = context.applicationContext.getSharedPreferences("list_user", android.content.Context.MODE_PRIVATE).edit()
                        editor.putLong("id", response.body()!!.id!!.toLong(),)
                        editor.putString("username", response.body()!!.username,)
                        editor.putString("password", response.body()!!.password)
                        editor.putString("address_gov", response.body()!!.address_gov)
                        editor.putString("address_municipale", response.body()!!.address_municipale)
                        editor.putString("image", response.body()!!.image)
                        editor.putString("phone", response.body()!!.phone.toString())
                        editor.putString("cin", response.body()!!.cin.toString())
                        editor.putString("niveau", response.body()!!.niveau)
                        editor.apply()*/

                        } else {
                            com.dev0jk.depanin.model.data.remote.entity.User(
                                response.body()!!.id!!.toLong(),
                                response.body()!!.username,
                                response.body()!!.password,
                                response.body()!!.address_gov,
                                response.body()!!.address_municipale,
                                response.body()!!.image,
                                response.body()!!.phone,
                                response.body()!!.cin,
                                response.body()!!.speciality,
                                false
                            )
                        }
                    readAllData.value = messageResult
                }

            }

            override fun onFailure(call: Call<com.dev0jk.depanin.model.data.remote.entity.User>, t: Throwable) {
                Log.println(Log.ASSERT, "======================>2", t.message.toString())

            }



        })

        return readAllData
    }

    fun addToFavorites(id_worker :Long,
                       id_client : Long
    ): LiveData<com.dev0jk.depanin.model.data.remote.entity.User> {
        var readAllData = MutableLiveData<com.dev0jk.depanin.model.data.remote.entity.User>()


        ServiceUserBuilder.apiWorker.addToFavorites(id_worker,id_client).enqueue(object :
            Callback<String> {

            override fun onResponse(

                call: Call<String>,
                response: Response<String>
            ) {
                Log.println(Log.ASSERT, "", response.message())
                }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.println(Log.ASSERT, "======================>2", t.message.toString())

            }



        })

        return readAllData
    }

    fun removeFromFavorites(id_worker :Long,
                       id_client : Long
    ): LiveData<com.dev0jk.depanin.model.data.remote.entity.User> {
        var readAllData = MutableLiveData<com.dev0jk.depanin.model.data.remote.entity.User>()


        ServiceUserBuilder.apiWorker.removeFromFavorites(id_worker,id_client).enqueue(object :
            Callback<String> {

            override fun onResponse(

                call: Call<String>,
                response: Response<String>
            ) {
                Log.println(Log.ASSERT, "", response.message())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.println(Log.ASSERT, "======================>2", t.message.toString())

            }



        })

        return readAllData
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

    suspend fun updateWorker(id : Long,
                           username: String?,
                           password : String?
    ): LiveData<ResponseUserModel> {
        val requestUserModel = RequestUserModel(id,username!!, password,null,null,null,null)
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

    suspend fun updateWorkerSpeciality(id : Long,
                             specialty: String,
    ): LiveData<ResponseUserModel> {
        val requestUserModel = RequestUserModel(id,null, null,null,null,null,null,null,specialty)
        var readAllData = MutableLiveData<ResponseUserModel>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")
        val code200=true
        try {


            ServiceUserBuilder.apiUser.UpdateWorkerSpeciality(id, requestUserModel).enqueue(object :
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

    suspend fun updateWorkerLocation(id : Long,
                                     address_municipale: String,
                                       address_gov: String,
    ): LiveData<ResponseUserModel> {
        val requestUserModel = RequestUserModel(id,null, null,address_gov,address_municipale,null,null,null,null)
        var readAllData = MutableLiveData<ResponseUserModel>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")
        val code200=true
        try {


            ServiceUserBuilder.apiUser.UpdateLocation(id, requestUserModel).enqueue(object :
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
}

