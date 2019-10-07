package com.example.amconsofttestproject.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amconsofttestproject.App
import com.example.amconsofttestproject.di.component.ViewModelComponent

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    private fun createDaggerDependencies() {
    injectDependency((application as App).getViewModelComponent())
    }

    protected abstract fun injectDependency(viewModelComponent: ViewModelComponent)
}