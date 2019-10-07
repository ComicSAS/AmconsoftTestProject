package com.example.amconsofttestproject.di.module

import com.example.amconsofttestproject.usecases.repository.database.UserDatabase
import dagger.Module
import dagger.Provides

@Module
class UserDatabaseModule(private val userDatabase: UserDatabase) {
    @Provides
    internal fun provideRoomDatabase(): UserDatabase = userDatabase
}