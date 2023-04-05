package com.dev0jk.depanin.model.entity.modelWorker

data class RequestUserModel(
    val id: Long,
    val username: String?,
    val password: String?,
    val address_gov: String?= null,
    val address_municipale: String?= null,
    val image: String?=null,
    val phone: Int?= null,
    val cin: String? = null,
    val speciality: String? = null
)
