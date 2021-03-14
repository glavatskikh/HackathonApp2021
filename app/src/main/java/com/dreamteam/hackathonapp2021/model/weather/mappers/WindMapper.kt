package com.dreamteam.hackathonapp2021.model.weather.mappers

import com.dreamteam.hackathonapp2021.data.api.weather.dto.WindDto
import com.dreamteam.hackathonapp2021.model.weather.Wind
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun windMapping(windDto: WindDto): Wind = withContext(Dispatchers.IO) {
    Wind(
        speed = windDto.speed,
        deg = windDto.deg
    )
}