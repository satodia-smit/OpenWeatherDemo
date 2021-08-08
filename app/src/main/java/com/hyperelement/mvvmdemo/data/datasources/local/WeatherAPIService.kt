package com.hyperelement.mvvmdemo.data.datasources.local

import com.hyperelement.mvvmdemo.data.entity.city.CityBaseRes
import com.hyperelement.mvvmdemo.BuildConfig
import retrofit2.http.*

interface WeatherAPIService {

    @GET("weather?appid=${BuildConfig.API_KEY}&units=metric")
    suspend fun getCityWeather(
        @Query("q") query: String
    ): CityBaseRes

}