package com.dev0jk.depanin.model.data.repository

import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.remote.WorkerRemote
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.modelWorker.ResponseUserModel
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.model.entity.modelWorker.UserModel
import com.dev0jk.depanin.utils.MessageResult

class WorkerRepository {

    val workerRemote = WorkerRemote()

    fun getRecommanded(loaction : String) : LiveData<ArrayList<User>?>{
        return workerRemote.getRecommendedWorker(loaction)
    }

    fun CreateSpecialtyOfWorker(userID: String, specialty: String): LiveData<MessageResult> {
        return workerRemote.createSpecialtyOfWorker(userID, specialty)
    }
    fun updateLocation(userId : String,location: Location) : LiveData<MessageResult>{
        return workerRemote.updateLocation(userId, location)
    }
    fun signUpWorker(username : String,password:String,address:String,phone:Int,cin:Int,niveau:String) : LiveData<ResponseUserModel>{
        return workerRemote.signUpWorker(username ,password,address,phone,cin,niveau)
    }
    fun getData(phone:Int) : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return workerRemote.getDataWorker(phone)
    }
    fun getAllWorker() : LiveData<List<com.dev0jk.depanin.model.data.remote.entity.User>>{
        return workerRemote.getAllWorker()
    }

}