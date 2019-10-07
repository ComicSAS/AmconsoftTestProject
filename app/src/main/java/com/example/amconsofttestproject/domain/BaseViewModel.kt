package com.example.amconsofttestproject.domain

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import com.example.amconsofttestproject.App

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    fun getContext() = getApplication<App>()
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}