package com.dreamteam.hackathonapp2021.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("previewURL")
    val previewURL: String,
    @SerialName("webformatURL")
    val webformatURL: String)