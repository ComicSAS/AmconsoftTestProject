package com.example.amconsofttestproject.di.component

import com.example.amconsofttestproject.di.module.UserInteractorModule
import com.example.amconsofttestproject.di.scope.UserInteractorScope
import com.example.amconsofttestproject.usecases.UserInteractor
import dagger.Component

@UserInteractorScope
@Component(modules = [UserInteractorModule::class], dependencies = [UserRepositoryComponent::class])
interface UserInteractorComponent {
    val userInteractor:UserInteractor
}