package com.dreamteam.hackathonapp2021.presentation.features.countries.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.di.Dependencies
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel.CountriesListViewModelImpl
import com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel.CountryListViewModelFactory
import kotlinx.android.synthetic.main.fragment_countries_list.*

class CountriesListFragment : Fragment() {

    private val viewModel: CountriesListViewModelImpl by viewModels {
        CountryListViewModelFactory(Dependencies.countriesRepository)
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
        view.findViewById<RecyclerView>(R.id.recycler_countries).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)
            val adapter = CountriesListAdapter { countryData ->
                listener?.onCountrySelected(countryData)
            }
            this.adapter = adapter
            loadDataToAdapter(adapter)
        }
    }

    private fun loadDataToAdapter(adapter: CountriesListAdapter) {
        viewModel.countriesOutput.observe(viewLifecycleOwner, { countriesList ->
            adapter.submitList(countriesList)
        })
        viewModel.progressBarVisibleLiveData.observe(viewLifecycleOwner, {
            when (it) {
                true -> lottie_view.isVisible
                else -> lottie_view.isInvisible
            }
        })
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface CountriesListItemClickListener {
        fun onCountrySelected(country: Country)
    }

    companion object {
        fun create() = CountriesListFragment()
    }
}
