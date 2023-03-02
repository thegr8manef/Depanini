package com.dev0jk.depanin.model.data.api


import android.provider.ContactsContract.CommonDataKinds.Phone
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.model.entity.modelWorker.RequestUserModel
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.model.entity.modelWorker.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiClientInterface {
    @POST("client/signup")
    fun sendReqClient(@Body requestModel: RequestUserModel) : Call<ResponseUserModel>

    @GET("clients")
    fun getDataClient(): Call<List<User>>
// ========================================================================
    @POST("worker/signup")
    fun sendReqWorker(@Body requestModel: RequestUserModel) : Call<ResponseUserModel>

    @GET("workers")
    fun getAllUsers(): Call<List<User>>
// ========================================================================
    @GET("/admin/search/user/{phone}")
    suspend fun getCurrency(@Path("phone") phone: Int): Response<Boolean>

    @POST("authenticate")
    fun authentificateUsers(@Body requestModel: RequestUserModel) : Call<ResponseUserModel>
}