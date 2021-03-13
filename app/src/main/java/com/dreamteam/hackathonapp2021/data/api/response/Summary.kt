package com.dreamteam.hackathonapp2021.data.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Summary(
    @SerialName("LOW")
    val lOW: Int,
    @SerialName("MAJOR")
    val mAJOR: Int,
    @SerialName("MODERATE")
    val mODERATE: Int,
    @SerialName("UNKNOWN")
    val uNKNOWN: Int
)