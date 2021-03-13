package com.dreamteam.hackathonapp2021.data.cache

import android.content.SharedPreferences
import com.dreamteam.hackathonapp2021.data.DataSource
import com.dreamteam.hackathonapp2021.data.DataSourceResult
import com.dreamteam.hackathonapp2021.data.api.response.Feature
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocalDataSource(private val preferences: SharedPreferences) : DataSource {

    override suspend fun hasData(): Boolean {
        return preferences.contains(PARAM_COUNTRIES)
    }

    override suspend fun getCountries(): DataSourceResult {
        val jsonList = preferences.getString(PARAM_COUNTRIES, null)
        return if (jsonList.isNullOrEmpty()) {
            DataSourceResult.Error("local store error")
        } else {
            val features: List<Feature> = Json.decodeFromString(jsonList)
            DataSourceResult.Success(features)
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