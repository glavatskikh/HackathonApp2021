package com.dreamteam.hackathonapp2021.data.api

import com.dreamteam.hackathonapp2021.data.CountriesDataSource
import com.dreamteam.hackathonapp2021.data.CountriesResult
import com.dreamteam.hackathonapp2021.data.api.response.CovidCountryStatusResponse
import com.dreamteam.hackathonapp2021.di.Dependencies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDataSource : CountriesDataSource {

    override fun hasData(): Boolean = false

    override fun getCountries(callback: (CountriesResult) -> Unit) {
        NetworkModule.api.getCountriesStatus(countryId = 29475334).enqueue(object :
            Callback<CovidCountryStatusResponse?> {
            override fun onFailure(call: Call<CovidCountryStatusResponse?>, t: Throwable) {
                callback(CountriesResult.Error(t.message ?: "something went wrong"))
            }

            override fun onResponse(
                call: Call<CovidCountryStatusResponse?>,
                response: Response<CovidCountryStatusResponse?>
            ) {
                response.body()?.features.orEmpty().also { features ->
                    Dependencies.localDataSource.putCountries(features) // fixme: dirty hack
                    callback(CountriesResult.Success(features))
                }
            }
        })
    }
}