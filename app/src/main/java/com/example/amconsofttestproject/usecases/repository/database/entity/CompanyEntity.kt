package com.example.amconsofttestproject.usecases.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "company")
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("bs")
    val bs: String
)