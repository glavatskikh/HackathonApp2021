package com.dreamteam.hackathonapp2021.data.cache

import android.content.SharedPreferences
import com.dreamteam.hackathonapp2021.data.CountriesDataSource
import com.dreamteam.hackathonapp2021.data.CountriesResult
import com.dreamteam.hackathonapp2021.data.api.response.Feature
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocalDataSource(private val preferences: SharedPreferences) : CountriesDataSource {

    override fun hasData(): Boolean {
//        return preferences.contains(PARAM_COUNTRIES)
        return false
    }

    override fun getCountries(callback: (CountriesResult) -> Unit) {
        val jsonList = preferences.getString(PARAM_COUNTRIES, null)
        if (jsonList.isNullOrEmpty()) {
            callback(CountriesResult.Error("local store error"))
        } else {
            val features: List<Feature> = Json.decodeFromString(jsonList)
            callback(CountriesResult.Success(features))
        }
    }

    fun putCountries(features: List<Feature>) {
        val jsonList: String = Json.encodeToString(features)
        preferences.edit().putString(PARAM_COUNTRIES, jsonList).apply()
    }

    companion object {
        private const val PARAM_COUNTRIES = "countries"
    }
}