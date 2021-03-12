package com.dreamteam.hackathonapp2021.data.remote.retrofit.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CovidCountryStatusResponse(
    @SerialName("dataset_last_updated")
    val datasetLastUpdated: String,
    @SerialName("features")
    val features: List<Feature>,
    @SerialName("summary")
    val summary: Summary,
    @SerialName("type")
    val type: String
)