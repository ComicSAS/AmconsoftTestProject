package com.example.amconsofttestproject.usecases.repository.server

import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity
import com.example.amconsofttestproject.utils.RxWrapper
import io.reactivex.Single
import retrofit2.Response

class UserCommunicator(private val apiService: ApiService) {

    fun getListUsers(): Single<Response<List<UserEntity>>> =
        apiService.getAllUsers().compose(RxWrapper.singleTransformer())


}