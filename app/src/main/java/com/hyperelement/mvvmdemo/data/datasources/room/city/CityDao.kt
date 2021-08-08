package com.hyperelement.mvvmdemo.data.datasources.room.city

import androidx.room.*

@Dao
interface CityDao {

    @Transaction
    @Insert
    suspend fun insertCity(cityEntity: CityEntity)

    @Query("SELECT * FROM ${CityEntity.TABLE_NAME} ORDER BY ${CityEntity.MEMBER_ID} DESC")
    suspend fun getCitites(): List<CityEntity>

    @Delete
    suspend fun deleteCity(cityEntity: CityEntity)

}