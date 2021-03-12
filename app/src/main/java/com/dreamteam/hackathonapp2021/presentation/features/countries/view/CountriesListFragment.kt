package com.dreamteam.hackathonapp2021.presentation.features.countries.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.di.CountryRepositoryProvider
import com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel.CountriesListViewModelImpl
import com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel.CountryListViewModelFactory

class CountriesListFragment : Fragment() {

    private val viewModel: CountriesListViewModelImpl by viewModels {
        CountryListViewModelFactory((requireActivity() as CountryRepositoryProvider).provideCountryRepository())
    }

    private var listener: CountriesListItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CountriesListItemClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        viewModel.countriesOutput.observe(viewLifecycleOwner, { countriesList ->
            Log.d("TAG", countriesList[0].toString())
        })
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface CountriesListItemClickListener {
        fun onCountrySelected(countryId: Int)
    }

    companion object {
        fun create() = CountriesListFragment()
    }
}
