package com.dev0jk.depanin.model.entity

data class User(var id : String? = null,
                var name : String? = null,
                var lastName :String? = null,
                val phone : String? = null,
                var userImage : String?= null,
                var type : String? = null,
                var address : String? =null,
                var specialty : String? = null)
