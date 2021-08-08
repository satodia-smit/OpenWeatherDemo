package com.hyperelement.mvvmdemo.ui.fragment.city

import androidx.lifecycle.MutableLiveData
import com.hyperelement.mvvmdemo.data.datasources.room.city.CityEntity
import com.hyperelement.mvvmdemo.data.repository.CityRepository
import com.hyperelement.mvvmdemo.utilities.ErrorState
import com.hyperelement.mvvmdemo.utilities.RootViewModel
import com.hyperelement.mvvmdemo.utilities.SuccessState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val TAG = "CityVM"

//ViewModel for saving the data and doing the city related task
class CityVM(
    private val mRepo: CityRepository
) : RootViewModel() {

    val cityList = MutableLiveData<List<CityEntity>>()

    init {
        loadCityFromDB()
    }


    fun insertCityIntoDB(city: String) {
        launch {
            try {
                withContext(Dispatchers.IO) {
                    city?.let {
                        val response = mRepo.insertCity(
                            CityEntity(
                                name = city
                            )
                        )
                    }
                }
            } catch (exception: Exception) {
            }
        }

    }

    fun loadCityFromDB() {
        Timber.d("loadCityFromDB() called")
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = mRepo.getCities()
                    cityList.postValue(response)
                    if (response.isEmpty()) {
                        Timber.d("NO USER AVAILABLE")
                    }
                }
            } catch (exception: Exception) {
            }
        }
    }


    fun deleteCity(cityEntity: CityEntity) {
        Timber.d("deleteCity() called with: cityEntity = $cityEntity")
        launch {
            try {
                withContext(Dispatchers.IO) {
                    mRepo.deleteCity(
                        cityEntity
                    )
                    mState.postValue(SuccessState())
                    Timber.d("deleteMember: SUCCESS")
                }
            } catch (exception: Exception) {
                mState.value = ErrorState()
            }
        }
    }
}