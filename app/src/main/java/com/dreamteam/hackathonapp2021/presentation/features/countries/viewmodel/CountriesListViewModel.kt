package com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dreamteam.hackathonapp2021.model.Country

internal abstract class CountriesListViewModel : ViewModel() {

    abstract val countriesOutput: LiveData<List<Country>>
}
