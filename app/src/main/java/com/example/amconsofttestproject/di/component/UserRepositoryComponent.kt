package com.example.amconsofttestproject.di.component

import com.example.amconsofttestproject.di.module.UserRepositoryModule
import com.example.amconsofttestproject.di.scope.UserRepositoryScope
import com.example.amconsofttestproject.usecases.repository.UserRepository
import dagger.Component

@UserRepositoryScope
@Component(modules = [UserRepositoryModule::class], dependencies = [ApiComponent::class, UserDataBaseComponent::class])
interface UserRepositoryComponent {
    val userRepository: UserRepository
}