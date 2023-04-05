package com.dev0jk.depanin.model.data.remote

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev0jk.depanin.model.data.api.ApiClientInterface
import com.dev0jk.depanin.model.data.service.RetrofitHelper
import com.dev0jk.depanin.model.data.service.ServiceUserBuilder
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.model.entity.modelWorker.RequestUserModel
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.utils.MessageResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    fun updateLocationUser(userId : String, location: Location) : LiveData<MessageResult>{
        return updateLocation(userId, location)
    }
    fun getDataUser(phone : Int) : LiveData<MessageResult>{
        return getData(phone)
    }

    fun authentificateUsers(
        username: String,
        password: String
    ): LiveData<com.dev0jk.depanin.model.data.remote.entity.User> {
        val user = com.dev0jk.depanin.model.data.remote.entity.User(
            null,
            username,
            password,null,null,null,null,null,null,false
        )
        var readAllData = MutableLiveData<com.dev0jk.depanin.model.data.remote.entity.User>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")
        val code200=true
        ServiceUserBuilder.apiUser.authentificateUsers(user).enqueue(object :
            Callback<com.dev0jk.depanin.model.data.remote.entity.User> {
            override fun onResponse(

                call: Call<com.dev0jk.depanin.model.data.remote.entity.User>,
                response: Response<com.dev0jk.depanin.model.data.remote.entity.User>
            ) {
                /*  Log.println(Log.ASSERT, "body", response.body().toString())

                var  messageResult : ResponseUserModel = if (response.code()==200){
                    Log.println(Log.ASSERT, "header", response.headers()["authorization"].toString())
                    ResponseUserModel(response.headers()["authorization"].toString(),true)

                }else{
                    Log.println(Log.ASSERT, "header", response.headers()["authorization"].toString())
                    Log.println(Log.ASSERT, "header", response.body().toString())
                    ResponseUserModel(response.headers()["authorization"].toString(),false)

                }
                readAllData.value= messageResult
                //mutableLiveData.value = response.body()
//                   response.body()?.results?.forEach{
//                       Log.println(Log.ASSERT, "", it.toString())
//                   }
            }
*/                if (response.isSuccessful) {
                    val messageResult: com.dev0jk.depanin.model.data.remote.entity.User =
                        if (response.body()!=null) {
                            // Log.println(Log.ASSERT, "header", response.body().toString())
                            Log.println(Log.ASSERT, "header+body", response.body().toString())
                            com.dev0jk.depanin.model.data.remote.entity.User(
                                response.body()!!.id,
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
                        } else {
                            //  Log.println(Log.ASSERT, "header", response.body().toString())
                            com.dev0jk.depanin.model.data.remote.entity.User(
                                response.body()!!.id,
                                username,
                                password,
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
                    // Log.println(Log.ASSERT, "message Result logging 1", messageResult.toString())
                }
            }


            override fun onFailure(call: Call<com.dev0jk.depanin.model.data.remote.entity.User>, t: Throwable) {

                var  messageResult : com.dev0jk.depanin.model.data.remote.entity.User = if (code200){
                    com.dev0jk.depanin.model.data.remote.entity.User(
                        null,
                        username,
                        password,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        false
                    )

                }else{
                    com.dev0jk.depanin.model.data.remote.entity.User(
                        null,
                        username,
                        password,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        false
                    )
                }
                readAllData.value= messageResult

            }


        })

        return readAllData
    }

    fun signUpClient(username:String,password:String,address_gov:String,address_municipale:String,image:String,phone:Int) : LiveData<com.dev0jk.depanin.model.data.remote.entity.User> {
        val user = com.dev0jk.depanin.model.data.remote.entity.User(null,username, password,address_gov,address_municipale,image,phone,null,null,false)

        var readAllData = MutableLiveData<com.dev0jk.depanin.model.data.remote.entity.User>()
        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")

        ServiceUserBuilder.apiClient.sendReqClient(user).enqueue(object :
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
    fun getAllClient(): LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>> {
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

        val call = apiService.getDataClient()

        call.enqueue(object : Callback<List<com.dev0jk.depanin.model.data.remote.entity.User>> {
            override fun onResponse(call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>, response: Response<List<com.dev0jk.depanin.model.data.remote.entity.User>>) {
                if (response.isSuccessful) {
                    val usersResponse = response.body()
                    Log.println(Log.ASSERT, "============>ResponseApiClient", "1")

                    Log.println(Log.ASSERT, "============>ResponseApiClient", usersResponse.toString())
                    // Do something with the list of users
                } else {
                    Log.println(Log.ASSERT, "============>ResponseApiClient", "2")
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<List<com.dev0jk.depanin.model.data.remote.entity.User>>, t: Throwable) {
                // Handle network error
                Log.println(Log.ASSERT, "============>ResponseApiClient", "3")
            }

        })
        return readAllData
    }

    suspend fun updateUser(id : Long,
                           username: String?,
                           password : String?
    ): LiveData<ResponseUserModel> {
        val requestUserModel = RequestUserModel(id,username!!, password,null,null,null,null)
        var readAllData = MutableLiveData<ResponseUserModel>()

        //val getPost = ServiceBuilder.api.sendReqWorker("manefDEv", "manef","TozeurHayMatar",99105580,9722325,"master")
        val code200=true
        try {


            ServiceUserBuilder.apiClient.UpdateClient(id, requestUserModel).enqueue(object :
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