package com.dreamteam.hackathonapp2021.data.api

import com.dreamteam.hackathonapp2021.data.api.response.CovidCountryStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesApi {

    @GET("feature-collection-translated")
    suspend fun getCountriesStatus(
        @Query("isMobile") isMobile: Boolean = false,
        @Query("locale") locale: String = "ru-RU",
        @Query("market") market: String = "RU",
        @Query("originId") countryId: Long
    ): Response<CovidCountryStatusResponse>
}