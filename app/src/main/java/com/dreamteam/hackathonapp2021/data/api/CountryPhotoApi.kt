package com.dreamteam.hackathonapp2021.data.api


import com.dreamteam.hackathonapp2021.data.api.response.ListCountryPhoto
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryPhotoApi {

    @GET("api/")
    suspend fun loadCountryPhoto(
        @Query("key") key: String = "20656667-4606499485653d73834ff01dc",
        @Query("q") query: String,
        @Query("image_type") image_type: String = "photo",
        @Query("lang") lang: String = "ru",
        @Query("pretty") pretty: Boolean = true,
        @Query("safesearch") safeSearch: Boolean = true,
        @Query("per_page") perPage: Int = 3
    ): ListCountryPhoto
}