package com.dreamteam.hackathonapp2021.di

import com.dreamteam.hackathonapp2021.repository.CountryRepository


internal interface CountryRepositoryProvider {
    fun provideCountryRepository(): CountryRepository
}