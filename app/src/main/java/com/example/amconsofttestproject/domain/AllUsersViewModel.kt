package com.example.amconsofttestproject.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.example.amconsofttestproject.presentation.widget.SingleLiveEvent
import com.example.amconsofttestproject.usecases.UserInteractor
import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity

class AllUsersViewModel(app: Application, private val userInteractor: UserInteractor) : BaseViewModel(app) {

    private val liveData = SingleLiveEvent<List<UserEntity>>()

    fun getLiveData(): LiveData<List<UserEntity>> = liveData

    @SuppressLint("CheckResult")
    fun getListUser() {
        userInteractor.getUserList().subscribe({ list -> liveData.value = list }, { liveData.value = null })
    }
}