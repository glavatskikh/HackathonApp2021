package com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.model.CountryRepository
import com.dreamteam.hackathonapp2021.model.Result
import kotlinx.coroutines.launch

internal class CountriesListViewModelImpl(
    private val repository: CountryRepository
) : CountriesListViewModel() {

    override val countriesOutput = MutableLiveData<List<Country>>()

    private val _progressBarVisibleLiveData = MutableLiveData<Boolean>()
    val progressBarVisibleLiveData: LiveData<Boolean> = _progressBarVisibleLiveData

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            try {
                _progressBarVisibleLiveData.value = true
                repository.getCountries { result ->
                    when (result) {
                        is Result.Success -> countriesOutput.postValue(result.countries)
                    }
                }
            } catch (error: Throwable) {
                error.printStackTrace()
            } finally {
                _progressBarVisibleLiveData.value = false
            }
        }
    }
}
