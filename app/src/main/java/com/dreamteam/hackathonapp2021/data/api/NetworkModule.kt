package com.dreamteam.hackathonapp2021.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val baseUrl = "https://www.skyscanner.ru/"
    private const val basePath = "g/can-i-go-map-api/map/"
    private const val baseUrlPhoto = "https://pixabay.com/"

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addNetworkInterceptor(loggingInterceptor)
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(httpClient)

    private val retrofitBuilderPhoto = Retrofit.Builder()
        .baseUrl(baseUrlPhoto)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(httpClient)

    private val retrofit = retrofitBuilder.build()
    private val retrofitPhoto = retrofitBuilderPhoto.build()

    val api: CountriesApi by lazy { retrofit.create(CountriesApi::class.java) }
    val apiPhoto: CountryPhotoApi by lazy { retrofitPhoto.create(CountryPhotoApi::class.java) }

}