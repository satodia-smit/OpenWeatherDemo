package com.hyperelement.mvvmdemo.di

import com.hyperelement.mvvmdemo.data.repository.CityRepository
import com.hyperelement.mvvmdemo.data.repository.WeatherRepository
import com.hyperelement.mvvmdemo.ui.fragment.city.CityVM
import com.hyperelement.mvvmdemo.ui.fragment.help.HelpVM
import com.hyperelement.mvvmdemo.ui.fragment.weather.WeatherVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Inject Repository
    single { CityRepository(get()) }
    single { WeatherRepository(get(), get()) }

    //Inject ViewModels
    viewModel {
        CityVM(
            get()
        )
    }
    viewModel {
        WeatherVM(
            get()
        )
    }

    viewModel {
        HelpVM(
        )
    }

}
