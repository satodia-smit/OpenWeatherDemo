package com.hyperelement.mvvmdemo.ui.fragment.weather

import com.hyperelement.mvvmdemo.data.entity.city.CityBaseRes
import androidx.lifecycle.MutableLiveData
import com.hyperelement.mvvmdemo.data.repository.WeatherRepository
import com.hyperelement.mvvmdemo.utilities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val TAG = "FragmentMoviesVM"

class WeatherVM(
    private val mRepo: WeatherRepository
) : RootViewModel() {

    val weatherData = MutableLiveData<CityBaseRes>()

    fun getSearchedItem(searchedItem: String) {
        mState.postValue(LoadingState())
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = mRepo.getCityWeather(searchedItem)
                    Timber.d("searchedItem API RESPONSE $response")
//                    weatherData.postValue(response)
                    if (response.cod != 200) {
                        Timber.d("NO Data AVAILABLE")
                        mState.postValue(ErrorState())
                    } else {
                        weatherData.postValue(response)
                        mState.postValue(SuccessState())

                    }
                }
            } catch (exception: Exception) {
                Timber.d("API EXCEPTION $exception")
            }
        }
    }
}