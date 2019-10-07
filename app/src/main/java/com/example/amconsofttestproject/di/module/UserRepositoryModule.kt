package com.example.amconsofttestproject.di.module

import com.example.amconsofttestproject.di.scope.UserRepositoryScope
import com.example.amconsofttestproject.usecases.repository.UserRepository
import com.example.amconsofttestproject.usecases.repository.database.UserDatabase
import com.example.amconsofttestproject.usecases.repository.server.UserCommunicator
import dagger.Module
import dagger.Provides

@Module
class UserRepositoryModule {

    @UserRepositoryScope
    @Provides
    internal fun provideRepository(userCommunicator: UserCommunicator, database: UserDatabase): UserRepository =
        UserRepository(userCommunicator, database)
}