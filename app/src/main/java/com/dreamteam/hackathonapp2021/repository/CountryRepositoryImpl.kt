package com.dreamteam.hackathonapp2021.repository

import com.dreamteam.hackathonapp2021.data.DataSource
import com.dreamteam.hackathonapp2021.data.DataSourceResult
import com.dreamteam.hackathonapp2021.model.Country

class CountryRepositoryImpl(
    private val onlineDataSource: DataSource,
    private val localDataSource: DataSource,
) : CountryRepository {

    override suspend fun getCountries(): CountriesResult {
        val dataSource = if (localDataSource.hasData()) localDataSource else onlineDataSource
        return when (val result = dataSource.getCountries()) {
            is DataSourceResult.Success -> {
                val countries = result.features.map { Country.map(it.properties) }
                CountriesResult.Success(countries)
            }
            is DataSourceResult.Error -> {
                CountriesResult.Error(result.message)
            }
        }
    }
}