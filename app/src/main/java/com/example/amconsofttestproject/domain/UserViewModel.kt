package com.example.amconsofttestproject.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.example.amconsofttestproject.presentation.widget.SingleLiveEvent
import com.example.amconsofttestproject.usecases.UserInteractor
import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity

class UserViewModel(app: Application, private val userInteractor: UserInteractor) : BaseViewModel(app) {

    private val liveData = SingleLiveEvent<UserEntity>()

    fun getLiveData(): LiveData<UserEntity> = liveData

    @SuppressLint("CheckResult")
    fun getUser(id: Int) {
        userInteractor.getUserById(id).subscribe { item -> liveData.value = item }
    }
}