package com.dreamteam.hackathonapp2021.di

import android.content.SharedPreferences
import com.dreamteam.hackathonapp2021.data.DataSource
import com.dreamteam.hackathonapp2021.data.api.ApiDataSource
import com.dreamteam.hackathonapp2021.data.cache.LocalDataSource
import com.dreamteam.hackathonapp2021.model.CountryRepositoryImpl

object Dependencies {

    // TODO: 13.03.2021 dirty
    var preferences: SharedPreferences? = null

    // Data
    val onlineDataSource by lazy {
        ApiDataSource()
    }

    val localDataSource by lazy {
        LocalDataSource(preferences!!)
    }

    val countriesRepository by lazy {
        createCountriesRepository(onlineDataSource, localDataSource)
    }

    private fun createCountriesRepository(
        onlineDataSource: DataSource,
        localDataSource: DataSource
    ): CountryRepositoryImpl {
        return CountryRepositoryImpl(
            onlineDataSource = onlineDataSource,
            localDataSource = localDataSource
        )
    }
}