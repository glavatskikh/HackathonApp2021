package com.dreamteam.hackathonapp2021.data

import com.dreamteam.hackathonapp2021.data.api.response.Feature

interface CountriesDataSource {

    fun hasData(): Boolean

    fun getCountries(callback: (CountriesResult) -> Unit)
}

sealed class CountriesResult {

    data class Success(val features: List<Feature>) : CountriesResult()

    data class Error(val message: String) : CountriesResult()
}