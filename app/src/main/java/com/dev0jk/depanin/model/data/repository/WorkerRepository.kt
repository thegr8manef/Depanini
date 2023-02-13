package com.dev0jk.depanin.model.data.repository

import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.remote.WorkerRemote
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.MessageResult

class WorkerRepository {

    val workerRemote = WorkerRemote()

    fun getRecommanded(loaction : String) : LiveData<ArrayList<User>?>{
        return workerRemote.getRecommendedWorker(loaction)
    }

    fun CreateSpecialtyOfWorker(userID: String, specialty: String): LiveData<MessageResult> {
        return workerRemote.CreateSpecialtyOfWorker(userID, specialty)
    }

}