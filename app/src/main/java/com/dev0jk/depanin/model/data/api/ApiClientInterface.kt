package com.dev0jk.depanin.model.data.api


import android.provider.ContactsContract.CommonDataKinds.Phone
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.model.entity.modelWorker.RequestUserModel
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.model.entity.modelWorker.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import rx.Observable

interface ApiClientInterface {
    @POST("client/signup")
    fun sendReqClient(@Body user: User) : Call<User>

    @GET("clients")
    fun getDataClient(): Call<List<User>>
// ========================================================================
    @POST("worker/signup")
    fun sendReqWorker(@Body user: User) : Call<User>

    @GET("workers")
    fun getAllUsers(): Call<List<User>>
// ========================================================================
    @GET("/admin/search/user/{phone}")
    suspend fun getCurrency(@Path("phone") phone: Int): Response<Boolean>

    @POST("authenticate")
    fun authentificateUsers(@Body user: User) : Call<User>

//=============================================================================
@PUT("/worker/modify/{id}")
suspend fun UpdateWorker(@Path("id") id: Long,
                         @Body username: RequestUserModel) : Call<ResponseUserModel>
//================================================================================
@PUT("/worker/modify/speciality/{id}")
suspend fun UpdateWorkerSpeciality(@Path("id") id: Long,
                         @Body speciality: RequestUserModel) : Call<ResponseUserModel>
//=================================================================================
@PUT("/client/modify/{id}")
suspend fun UpdateClient(@Path("id") id: Long,
                             @Body username: RequestUserModel) : Call<ResponseUserModel>

@GET("/admin/search/{address_municipale}/{address_gov}")
 fun getRecommendedWorker(@Path("address_municipale") address_municipale : String,
                                 @Path("address_gov")address_gov : String)   : Call<List<User>>

    @GET("/worker/filter/{speciality}")
    fun filterbySpeciality(@Path("speciality") speciality : String)   : Call<List<User>>

    @GET("/users/searchbyname/{username}")
    fun searchByUsername(@Path("username") username : String)   : Call<List<User>>

}