package com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dreamteam.hackathonapp2021.data.api.weather.WeatherNetworkModule
import kotlinx.serialization.ExperimentalSerializationApi

class CountryDetailsViewModelFactory : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        CountryDetailsViewModel::class.java -> CountryDetailsViewModel(
            weatherApi =WeatherNetworkModule.weatherApi
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}
