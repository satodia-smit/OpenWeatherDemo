package com.hyperelement.mvvmdemo.data.repository

import com.hyperelement.mvvmdemo.data.datasources.room.city.CityDao
import com.hyperelement.mvvmdemo.data.datasources.room.city.CityEntity

class CityRepository(
    private val cityDao: CityDao
) {
    suspend fun getCities(): List<CityEntity> {
        return cityDao.getCitites()
    }

    suspend fun insertCity(cityEntity: CityEntity) {
        cityDao.insertCity(cityEntity)
    }

    suspend fun deleteCity(cityEntity: CityEntity) {
        cityDao.deleteCity(cityEntity)
    }
}