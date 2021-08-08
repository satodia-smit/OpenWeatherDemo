package com.hyperelement.mvvmdemo.data.repository


import com.hyperelement.mvvmdemo.data.entity.city.CityBaseRes
import android.content.Context
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.datasources.local.WeatherAPIService
import com.hyperelement.mvvmdemo.utilities.AppUtils
import java.net.UnknownHostException


class WeatherRepository(
    private val context: Context,
    private val weatherAPIService: WeatherAPIService
) {
    suspend fun getCityWeather(aQuery: String): CityBaseRes {
        if (!AppUtils.hasNetwork(context)) {
            throw UnknownHostException(context.getString(R.string.err_no_internet))
        }
        return weatherAPIService.getCityWeather(aQuery)
    }
}