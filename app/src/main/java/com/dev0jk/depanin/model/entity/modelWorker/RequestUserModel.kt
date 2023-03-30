package com.dev0jk.depanin.model.entity.modelWorker

data class RequestUserModel(
    val username: String,
    val password: String?,
    val address_gov: String?= null,
    val address_municipale: String?= null,
    val phone: Int?= null,
    val cin: Int? = null,
    val niveau: String? = null
)
