package com.dev0jk.depanin.model.data.service

import com.dev0jk.depanin.model.data.api.ApiClientInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceUserBuilder {
    /*    private val client = OkHttpClient.Builder().build()

private val retrofit = Retrofit.Builder()
    .baseUrl("http://localhost:8080") // change this IP for testing by your actual machine IP
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

fun<T> buildService(service: Class<T>): T{
    return retrofit.create(service)
}*/
    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    //.addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b3VuZXMiLCJleHAiOjE2Nzg4MDY3NTYsImlhdCI6MTY3NzA3ODc1Nn0.ay8VaBSZvPFTZ4Bz7Xvz8DZGtuVRFdK3al26s7ABmC0")
                    .build()
                chain.proceed(request)
            })
            .build()
        Retrofit.Builder()
            .baseUrl("https://depaninibackend-production-c4b8.up.railway.app")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val apiClient : ApiClientInterface by lazy {
        retrofit.create(ApiClientInterface::class.java)
    }

    val apiWorker : ApiClientInterface by lazy {
        retrofit.create(ApiClientInterface::class.java)
    }

    val apiUser : ApiClientInterface by lazy {
        retrofit.create(ApiClientInterface::class.java)
    }
}