package com.dev0jk.depanin.vm

import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.repository.WorkerRepository
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.model.entity.modelWorker.UserModel
import com.dev0jk.depanin.utils.MessageResult

class WorkerVM {

    val workerRepository = WorkerRepository()

    fun getRecommendedWorker(address_municipale : String,address_gov:String) : LiveData<com.dev0jk.depanin.model.data.remote.entity.User> {
        return workerRepository.getRecommendedWorker(address_municipale,address_gov)
    }

    fun CreateSpecialtyOfWorker(userID: String, specialty: String): LiveData<MessageResult> {
        return workerRepository.CreateSpecialtyOfWorker(userID, specialty)
    }
    fun updateLocation(userId : String,location: Location) : LiveData<MessageResult>{
        return workerRepository.updateLocation(userId, location)
    }
    fun signUpWorker(username:String,password:String,address_gov:String,address_municipale:String,phone:Int,cin:Int,niveau:String) : LiveData<ResponseUserModel>{
        return workerRepository.signUpWorker(username, password, address_gov,address_municipale, phone, cin, niveau)
    }
    fun getData(phone:Int) : LiveData<MessageResult>{
        return workerRepository.getData(phone)
    }
    fun getAllWorker() : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return workerRepository.getAllWorker()
    }
    suspend fun updateWorker(id: Long, username: String,password: String) : LiveData<ResponseUserModel>{
        return workerRepository.updateWorker(id,username,password)
    }

}