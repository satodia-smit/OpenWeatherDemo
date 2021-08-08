package com.hyperelement.mvvmdemo.ui.fragment.weather

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.databinding.FragmentWeatherBinding
import com.hyperelement.mvvmdemo.utilities.*
import kotlinx.android.synthetic.main.fragment_weather.*


//To receive the city name and display weather data
class FragmentWeather :
    BaseFragment<WeatherVM>(R.layout.fragment_weather, WeatherVM::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentWeatherBinding>()?.viewModel = viewModel

        arguments?.let { FragmentWeatherArgs.fromBundle(it).cityName }?.let {
            viewModel.getSearchedItem(
                it
            )
        }


        viewModel.mState.observe(this, Observer { state ->
            when (state) {
                is LoadingState -> {
                    pbLoader.visibility = View.VISIBLE

                }
                is ErrorState -> {
                    pbLoader.visibility = View.GONE

                }
                is SuccessState -> {
                    pbLoader.visibility = View.GONE
                }
                is MsgState -> {

                }
                is EmptyState -> {

                }
            }
        })
    }
}