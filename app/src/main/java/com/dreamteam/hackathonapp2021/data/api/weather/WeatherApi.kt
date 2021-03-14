package com.dreamteam.hackathonapp2021.data.api.weather

import com.dreamteam.hackathonapp2021.data.api.weather.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("units") property: String = "metric",
            @Query("lang") lang: String = "RU",
            @Query("appid") appid: String = "f8b56c73a876a4ac1996b36bb56af7e0"
    ): WeatherResponseDto
}