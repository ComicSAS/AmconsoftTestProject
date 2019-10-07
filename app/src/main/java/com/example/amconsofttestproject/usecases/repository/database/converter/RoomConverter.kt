package com.example.amconsofttestproject.usecases.repository.database.converter

import androidx.room.TypeConverter
import com.example.amconsofttestproject.usecases.repository.database.entity.AddressEntity
import com.example.amconsofttestproject.usecases.repository.database.entity.CompanyEntity
import com.example.amconsofttestproject.usecases.repository.database.entity.GeoEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverter {

    @TypeConverter
    fun convertGeoEntityToJson(geo: GeoEntity): String {
        val gson = Gson()
        return gson.toJson(geo)
    }

    @TypeConverter
    fun convertJsonToGeoEntity(json: String): GeoEntity {
        val gson = Gson()
        val type = object : TypeToken<GeoEntity>() {}.type
        return gson.fromJson(json, type)
    }


    @TypeConverter
    fun convertAddressToJson(address: AddressEntity): String {
        val gson = Gson()
        return gson.toJson(address)
    }

    @TypeConverter
    fun convertJsonToAddressEntity(json: String): AddressEntity {
        val gson = Gson()
        val type = object : TypeToken<AddressEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun convertCompanyToJson(company: CompanyEntity): String {
        val gson = Gson()
        return gson.toJson(company)
    }

    @TypeConverter
    fun convertJsonToCompanyEntity(json: String): CompanyEntity {
        val gson = Gson()
        val type = object : TypeToken<CompanyEntity>() {}.type
        return gson.fromJson(json, type)
    }
}