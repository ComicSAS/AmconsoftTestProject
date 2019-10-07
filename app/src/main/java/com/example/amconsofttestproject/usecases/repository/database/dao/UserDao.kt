package com.example.amconsofttestproject.usecases.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(list: List<UserEntity>)


}