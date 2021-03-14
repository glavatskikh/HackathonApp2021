package com.dreamteam.hackathonapp2021.data.api.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseDto (
        val coord: CoordDto,
        val weather: List<WeatherDto>,
        @SerialName("main")
        val temperature: TemperatureDto,
        val wind: WindDto
)