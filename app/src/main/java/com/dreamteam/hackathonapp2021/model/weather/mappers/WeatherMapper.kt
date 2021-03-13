package com.dreamteam.hackathonapp2021.model.weather.mappers

import com.dreamteam.hackathonapp2021.data.api.weather.dto.WeatherDto
import com.dreamteam.hackathonapp2021.model.weather.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun weatherMapping(weatherDto: List<WeatherDto>): List<Weather> = withContext(Dispatchers.IO) {

    weatherDto.map { it ->
        Weather(
            id = it.id,
            main = it.main,
            description = it.description
        )
    }

}