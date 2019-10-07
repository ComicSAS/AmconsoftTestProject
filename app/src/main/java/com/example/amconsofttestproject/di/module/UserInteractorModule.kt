package com.example.amconsofttestproject.di.module

import com.example.amconsofttestproject.di.scope.UserInteractorScope
import com.example.amconsofttestproject.usecases.UserInteractor
import com.example.amconsofttestproject.usecases.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class UserInteractorModule {
    @Provides
    @UserInteractorScope
    internal fun provideUserInteractor(userRepository: UserRepository): UserInteractor = UserInteractor(userRepository)
}