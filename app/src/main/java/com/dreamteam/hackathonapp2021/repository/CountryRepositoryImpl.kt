package com.dreamteam.hackathonapp2021.repository

import com.dreamteam.hackathonapp2021.data.remote.RemoteDataSource
import com.dreamteam.hackathonapp2021.model.Country

class CountryRepositoryImpl(
    private val remoteDataResource: RemoteDataSource
) : CountryRepository {

    override suspend fun loadCountries(): List<Country> {
        return remoteDataResource.loadCovidCountriesStatus()
    }
}