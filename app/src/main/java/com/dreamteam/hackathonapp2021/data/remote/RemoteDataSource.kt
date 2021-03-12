package com.dreamteam.hackathonapp2021.data.remote

import com.dreamteam.hackathonapp2021.model.Country

interface RemoteDataSource {
    suspend fun loadCovidCountriesStatus(): List<Country>
}