package com.dreamteam.hackathonapp2021.data.api

import com.dreamteam.hackathonapp2021.data.DataSource
import com.dreamteam.hackathonapp2021.data.DataSourceResult
import com.dreamteam.hackathonapp2021.di.Dependencies

class ApiDataSource : DataSource {

    override suspend fun hasData(): Boolean = false

    override suspend fun getCountries(): DataSourceResult {
        val response = NetworkModule.api.getCountriesStatus(countryId = 29475334)
        if (response.isSuccessful) {
            response.body()?.features?.let { features ->
                Dependencies.localDataSource.putCountries(features) // fixme: dirty hack
                return DataSourceResult.Success(features)
            }
            return DataSourceResult.Error("body is empty")
        }
        return DataSourceResult.Error("${response.code()} ${response.errorBody()}")
    }
}