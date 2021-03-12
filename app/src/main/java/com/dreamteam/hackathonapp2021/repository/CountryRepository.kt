package com.dreamteam.hackathonapp2021.repository

import com.dreamteam.hackathonapp2021.model.Country

interface CountryRepository {
    suspend fun loadCountries(): List<Country>
}
