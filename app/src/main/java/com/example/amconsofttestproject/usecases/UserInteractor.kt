package com.example.amconsofttestproject.usecases

import com.example.amconsofttestproject.usecases.repository.UserRepository
import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserInteractor(private val repository: UserRepository) {

    fun getUserList(): Single<List<UserEntity>> =
        repository.getUserList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun getUserById(id: Int): Single<UserEntity> =
        repository.getUserById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}