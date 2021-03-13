package com.dreamteam.hackathonapp2021.data.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("country_id")
    val countryId: String,
    @SerialName("country_name")
    val countryName: String,
    @SerialName("restrictions")
    val restrictions: Restrictions? = null,
    @SerialName("centroid")
    val centroid: Centroid
)