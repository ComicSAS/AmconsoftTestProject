package com.example.amconsofttestproject.usecases.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "geo")
data class GeoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)