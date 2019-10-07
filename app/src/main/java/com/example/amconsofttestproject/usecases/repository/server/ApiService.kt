package com.example.amconsofttestproject.usecases.repository.server

import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    fun getAllUsers(): Single<Response<List<UserEntity>>>

}