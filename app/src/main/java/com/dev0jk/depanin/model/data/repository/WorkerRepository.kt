package com.dev0jk.depanin.model.data.repository

import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.remote.WorkerRemote
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.model.entity.modelWorker.UserModel
import com.dev0jk.depanin.utils.MessageResult

class WorkerRepository {

    val workerRemote = WorkerRemote()

     fun getRecommendedWorker(address_municipale : String, address_gov:String) : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return workerRemote.getRecommendedWorker(address_municipale, address_gov)
    }

    fun filterbySpeciality(speciality: String) : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return workerRemote.filterbySpeciality(speciality)
    }
    fun searchByUsername(speciality: String) : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return workerRemote.searchByUsername(speciality)
    }

    fun CreateSpecialtyOfWorker(userID: String, specialty: String): LiveData<MessageResult> {
        return workerRemote.createSpecialtyOfWorker(userID, specialty)
    }
    fun updateLocation(userId : String,location: Location) : LiveData<MessageResult>{
        return workerRemote.updateLocation(userId, location)
    }
    fun signUpWorker(username:String,password:String,address_gov:String,address_municipale:String,image:String,phone:Int,cin:String,speciality:String) : LiveData<com.dev0jk.depanin.model.data.remote.entity.User>{
        return workerRemote.signUpWorker(username, password, address_gov,address_municipale,image, phone, cin, speciality)
    }
    fun getData(phone:Int) : LiveData<MessageResult>{
        return workerRemote.getDataWorker(phone)
    }
    fun getAllWorker() : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return workerRemote.getAllWorker()
    }

    suspend fun updateWorker(id: Long, username: String?,password: String?) : LiveData<ResponseUserModel>{
        return workerRemote.updateWorker(id,username!!,password!!)
    }
     suspend fun updateWorkerSpeciality(id: Long, specialty: String) : LiveData<ResponseUserModel>{
        return workerRemote.updateWorkerSpeciality(id, specialty )
    }
}