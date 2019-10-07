package com.example.amconsofttestproject.usecases.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.amconsofttestproject.usecases.repository.database.converter.RoomConverter
import com.example.amconsofttestproject.usecases.repository.database.dao.UserDao
import com.example.amconsofttestproject.usecases.repository.database.entity.AddressEntity
import com.example.amconsofttestproject.usecases.repository.database.entity.CompanyEntity
import com.example.amconsofttestproject.usecases.repository.database.entity.GeoEntity
import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity

@Database(entities = [UserEntity::class, AddressEntity::class, CompanyEntity::class, GeoEntity::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}