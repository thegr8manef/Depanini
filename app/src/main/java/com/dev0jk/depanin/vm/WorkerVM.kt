package com.dev0jk.depanin.vm

import androidx.lifecycle.LiveData
import com.dev0jk.depanin.model.data.repository.WorkerRepository
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.MessageResult

class WorkerVM {

    val workerRepository = WorkerRepository()

    fun getRecommanded(loaction : String) : LiveData<ArrayList<User>?> {
        return workerRepository.getRecommanded(loaction)
    }

    fun CreateSpecialtyOfWorker(userID: String, specialty: String): LiveData<MessageResult> {
        return workerRepository.CreateSpecialtyOfWorker(userID, specialty)
    }
}