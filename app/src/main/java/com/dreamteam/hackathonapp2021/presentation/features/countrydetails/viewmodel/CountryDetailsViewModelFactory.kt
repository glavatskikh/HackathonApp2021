package com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dreamteam.hackathonapp2021.repository.CountryRepository

class CountryDetailsViewModelFactory(private val repository: CountryRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CountryDetailsViewModelImpl(repository) as T
}
