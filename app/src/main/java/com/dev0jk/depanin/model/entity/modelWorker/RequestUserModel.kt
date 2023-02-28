package com.dev0jk.depanin.model.entity.modelWorker

data class RequestUserModel(
    val username: String,
    val password: String,
    val address: String,
    val phone: Int,
    val cin: Int? = null,
    val niveau: String? = null
)
