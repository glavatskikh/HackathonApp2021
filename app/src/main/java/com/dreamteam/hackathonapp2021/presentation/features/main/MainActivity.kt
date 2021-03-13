package com.dreamteam.hackathonapp2021.presentation.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.presentation.features.countries.view.CountriesListFragment
import com.dreamteam.hackathonapp2021.presentation.features.countries.view.CountriesListFragment.CountriesListItemClickListener
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.view.CountryDetailsFragment
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.view.CountryDetailsFragment.CountryDetailsBackClickListener

class MainActivity : AppCompatActivity(),
    CountriesListItemClickListener,
    CountryDetailsBackClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            routeToCountriesList()
        }
    }

    override fun onCountrySelected(country: Country) {
        routeToCountriesDetails(country)
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

    private fun routeToCountriesDetails(country: Country) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                CountryDetailsFragment.create(country),
                CountryDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${CountryDetailsFragment::class.java.simpleName}")
            .commit()
    }

//    private fun routeBack() {
//        supportFragmentManager.popBackStack()
//    }
}