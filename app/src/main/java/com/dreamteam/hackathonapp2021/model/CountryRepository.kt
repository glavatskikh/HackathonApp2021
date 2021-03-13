package com.dreamteam.hackathonapp2021.model


interface CountryRepository {
    suspend fun getCountries(callback: (Result) -> Unit)
}

sealed class Result {

    data class Success(val countries: List<Country>) : Result()

    data class Error(val message: String) : Result()
}