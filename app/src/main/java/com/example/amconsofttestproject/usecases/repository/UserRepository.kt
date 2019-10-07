package com.example.amconsofttestproject.usecases.repository

import com.example.amconsofttestproject.usecases.repository.database.UserDatabase
import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity
import com.example.amconsofttestproject.usecases.repository.server.UserCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(private val userCommunicator: UserCommunicator, private val database: UserDatabase) {

    fun getUserList(): Single<List<UserEntity>> =
        userCommunicator.getListUsers()
            .flatMap { response ->
                if (response.isSuccessful)
                    database.userDao().insertUsers(response.body()!!)
                Single.just(database.userDao().getAllUsers())
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getUserById(id: Int): Single<UserEntity> = Single.just(database.userDao().getUserById(id))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}