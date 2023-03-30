package com.dev0jk.depanin.model.data.service

import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import com.dev0jk.depanin.utils.Strings
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


object RetrofitHelper {
    @JvmStatic
    fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Strings().URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}