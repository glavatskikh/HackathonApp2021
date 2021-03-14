package com.dreamteam.hackathonapp2021.model.weather.mappers

import com.dreamteam.hackathonapp2021.data.api.weather.dto.TemperatureDto
import com.dreamteam.hackathonapp2021.model.weather.Temperature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun temperatureMapping(temperatureDto: TemperatureDto): Temperature =
    withContext(Dispatchers.IO) {
        Temperature(
            temp = temperatureDto.temp
        )
    }