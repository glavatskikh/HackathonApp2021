package com.dreamteam.hackathonapp2021.data

import com.dreamteam.hackathonapp2021.data.api.response.Feature

interface DataSource {

    suspend fun hasData(): Boolean

    suspend fun getCountries(): DataSourceResult
}

sealed class DataSourceResult {

    data class Success(val features: List<Feature>) : DataSourceResult()

    data class Error(val message: String) : DataSourceResult()
}