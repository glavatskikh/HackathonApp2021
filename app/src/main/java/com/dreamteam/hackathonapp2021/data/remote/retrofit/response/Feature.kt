package com.dreamteam.hackathonapp2021.data.remote.retrofit.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    @SerialName("properties")
    val properties: Properties,
    @SerialName("type")
    val type: String
)