package com.dreamteam.hackathonapp2021.presentation.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.data.remote.retrofit.RetrofitDataSource
import com.dreamteam.hackathonapp2021.di.CountryRepositoryProvider
import com.dreamteam.hackathonapp2021.di.NetworkModule
import com.dreamteam.hackathonapp2021.presentation.features.countries.view.CountriesListFragment
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.view.CountryDetailsFragment
import com.dreamteam.hackathonapp2021.repository.CountryRepository
import com.dreamteam.hackathonapp2021.repository.CountryRepositoryImpl

class MainActivity : AppCompatActivity(),
    CountriesListFragment.CountriesListItemClickListener,
    CountryDetailsFragment.CountryDetailsBackClickListener,
    CountryRepositoryProvider {

    private val networkModule = NetworkModule()
    private val remoteDataSource = RetrofitDataSource(networkModule.api)
    private val countryRepository = CountryRepositoryImpl(remoteDataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            routeToCountriesList()
        }
    }

    override fun onCountrySelected(countryId: Long) {
        routeToCountriesDetails(countryId)
    }

    override fun onCountryDeselected() {
//        routeBack()
    }

    private fun routeToCountriesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                CountriesListFragment.create(),
                CountriesListFragment::class.java.simpleName
            )
            .addToBackStack("trans:${CountriesListFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeToCountriesDetails(movieId: Long) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                CountryDetailsFragment.create(movieId),
                CountryDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${CountryDetailsFragment::class.java.simpleName}")
            .commit()
    }

//    private fun routeBack() {
//        supportFragmentManager.popBackStack()
//    }

    override fun provideCountryRepository(): CountryRepository = countryRepository
}