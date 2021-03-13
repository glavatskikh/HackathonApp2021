package com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dreamteam.hackathonapp2021.repository.CountryRepository

class CountryListViewModelFactory(private val repository: CountryRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CountriesListViewModelImpl(repository) as T
}
