package com.example.amconsofttestproject.di.component

import com.example.amconsofttestproject.di.module.ApiModule
import com.example.amconsofttestproject.di.scope.ApiScope
import com.example.amconsofttestproject.usecases.repository.server.UserCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val userCommunicator: UserCommunicator
}