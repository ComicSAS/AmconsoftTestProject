package com.example.amconsofttestproject

import android.app.Application
import androidx.room.Room
import com.example.amconsofttestproject.di.component.*
import com.example.amconsofttestproject.di.module.*
import com.example.amconsofttestproject.usecases.repository.database.UserDatabase

class App : Application() {

    private var viewModelComponent: ViewModelComponent? = null
    private var database: UserDatabase? = null

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
    }

    fun getViewModelComponent(): ViewModelComponent {
        return this!!.viewModelComponent!!
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, UserDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger() {

        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerUserDataBaseComponent.builder()
            .userDatabaseModule(UserDatabaseModule(this!!.database!!))
            .build()

        val repositoryComponent = DaggerUserRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .userDataBaseComponent(databaseComponent)
            .userRepositoryModule(UserRepositoryModule())
            .build()

        val interactorComponent = DaggerUserInteractorComponent.builder()
            .userRepositoryComponent(repositoryComponent)
            .userInteractorModule(UserInteractorModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .userInteractorComponent(interactorComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }
}