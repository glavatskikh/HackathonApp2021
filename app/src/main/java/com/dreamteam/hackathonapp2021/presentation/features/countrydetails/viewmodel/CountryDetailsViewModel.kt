package com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamteam.hackathonapp2021.data.api.weather.WeatherApi
import com.dreamteam.hackathonapp2021.model.weather.Temperature
import com.dreamteam.hackathonapp2021.model.weather.Weather
import com.dreamteam.hackathonapp2021.model.weather.Wind
import com.dreamteam.hackathonapp2021.model.weather.mappers.temperatureMapping
import com.dreamteam.hackathonapp2021.model.weather.mappers.weatherMapping
import com.dreamteam.hackathonapp2021.model.weather.mappers.windMapping
import kotlinx.coroutines.launch
import java.lang.Exception

class CountryDetailsViewModel(
    private val weatherApi: WeatherApi
): ViewModel() {

    private val _temperatureData = MutableLiveData<Temperature>()
    val temperatureData: LiveData<Temperature> get() = _temperatureData

    private val _windData = MutableLiveData<Wind>()
    val windData: LiveData<Wind> get() = _windData

    private val _weatherData = MutableLiveData<Weather>()
    val weatherData: LiveData<Weather> get() = _weatherData

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            loadWeather(lat, lon)
        }
    }

    private suspend fun loadWeather(lat: Double, lon: Double){
        try{
            val weatherResponseDto = weatherApi.getWeather(lat, lon)
            val weathers = weatherMapping(weatherResponseDto.weather)
            val weather = weathers[0]
            _weatherData.postValue(weather)
            val temperature = temperatureMapping(weatherResponseDto.temperature)
            _temperatureData.postValue(temperature)
            val wind = windMapping(weatherResponseDto.wind)
            _windData.postValue(wind)
        } catch (e: Exception) {
            Log.i("Error getting weather", e.message.toString())
        }
    }
}