package com.dreamteam.hackathonapp2021.data.api.weather.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto (
    val id: Long,
    val main: String,
    val description: String,
)