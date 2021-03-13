package com.dreamteam.hackathonapp2021.model

interface CountryRepository {
    suspend fun getCountries(): CountriesResult
}

sealed class CountriesResult {

    data class Success(val countries: List<Country>) : CountriesResult()

    data class Error(val message: String) : CountriesResult()
}