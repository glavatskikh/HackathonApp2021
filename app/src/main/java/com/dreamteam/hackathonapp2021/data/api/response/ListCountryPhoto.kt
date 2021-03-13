package com.dreamteam.hackathonapp2021.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListCountryPhoto(
    @SerialName("total")
    val total: Int,
    @SerialName("hits")
    val hits: List<Photo>
)