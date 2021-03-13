package com.dreamteam.hackathonapp2021.data.api.weather.dto

import kotlinx.serialization.Serializable

@Serializable
data class WindDto (
    val speed: Double,
    val deg: Long
)
