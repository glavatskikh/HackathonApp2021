package com.dreamteam.hackathonapp2021.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Centroid(
    @SerialName("lat")
    val lat: Double,
    @SerialName("lng")
    val lng: Double
)