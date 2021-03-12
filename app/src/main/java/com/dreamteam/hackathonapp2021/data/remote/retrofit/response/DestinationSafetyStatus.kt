package com.dreamteam.hackathonapp2021.data.remote.retrofit.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DestinationSafetyStatus(
    @SerialName("casesDeltaPercent7Days")
    val casesDeltaPercent7Days: Int,
    @SerialName("casesLagPrevious7Days")
    val casesLagPrevious7Days: Int,
    @SerialName("casesLagRecent7Days")
    val casesLagRecent7Days: Int,
    @SerialName("casesPredictionStatus")
    val casesPredictionStatus: Int,
    @SerialName("epiPrevalencePrevious")
    val epiPrevalencePrevious: Double,
    @SerialName("epiPrevalenceRecent")
    val epiPrevalenceRecent: Double,
    @SerialName("population")
    val population: Int,
    @SerialName("status")
    val status: String
)