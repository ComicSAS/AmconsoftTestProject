package com.example.amconsofttestproject.di.component

import com.example.amconsofttestproject.di.module.UserDatabaseModule
import com.example.amconsofttestproject.usecases.repository.database.UserDatabase
import dagger.Component

@Component(modules = [UserDatabaseModule::class])
interface UserDataBaseComponent {
    val userDatabase: UserDatabase
}