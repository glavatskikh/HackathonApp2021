package com.dreamteam.hackathonapp2021.data.remote.retrofit

import com.dreamteam.hackathonapp2021.data.remote.retrofit.response.CovidCountryStatusResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidCountryStatusApiService {

    @GET("feature-collection-translated")
    suspend fun loadCovidCountriesStatus(
        @Query("isMobile") isMobile: Boolean = false,
        @Query("locale") locale: String = "ru-RU",
        @Query("market") market: String = "RU",
        @Query("originId") countryId: Long
    ): CovidCountryStatusResponse
}