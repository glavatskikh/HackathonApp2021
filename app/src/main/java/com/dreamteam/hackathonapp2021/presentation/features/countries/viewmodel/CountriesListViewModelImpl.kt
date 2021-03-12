package com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.repository.CountryRepository
import kotlinx.coroutines.launch

internal class CountriesListViewModelImpl(private val repository: CountryRepository) :
    CountriesListViewModel() {

    override val countriesOutput = MutableLiveData<List<Country>>()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            countriesOutput.postValue(repository.loadCountries())
        }
    }
}
