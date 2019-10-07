package com.example.amconsofttestproject.di.module

import android.app.Application
import com.example.amconsofttestproject.App
import com.example.amconsofttestproject.di.scope.ViewModelScope
import com.example.amconsofttestproject.domain.AllUsersViewModel
import com.example.amconsofttestproject.domain.UserViewModel
import com.example.amconsofttestproject.usecases.UserInteractor
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun provideAllUsersViewModel(userInteractor: UserInteractor): AllUsersViewModel =
        AllUsersViewModel(app, userInteractor)

    @ViewModelScope
    @Provides
    internal fun provideUserViewModel(userInteractor: UserInteractor): UserViewModel =
        UserViewModel(app, userInteractor)
}