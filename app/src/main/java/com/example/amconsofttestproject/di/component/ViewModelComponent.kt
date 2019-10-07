package com.example.amconsofttestproject.di.component

import com.example.amconsofttestproject.di.module.ViewModelModule
import com.example.amconsofttestproject.di.scope.ViewModelScope
import com.example.amconsofttestproject.presentation.activities.second.DetailSecondActivity
import com.example.amconsofttestproject.presentation.activities.second.SecondActivity
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [UserInteractorComponent::class])
interface ViewModelComponent {
    fun inject(activity: SecondActivity)
    fun inject(activity: DetailSecondActivity)
}