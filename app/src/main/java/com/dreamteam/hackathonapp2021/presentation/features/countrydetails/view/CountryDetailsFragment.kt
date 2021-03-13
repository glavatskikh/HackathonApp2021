package com.dreamteam.hackathonapp2021.presentation.features.countrydetails.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.di.Dependencies
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel.CountryDetailsViewModelFactory
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel.CountryDetailsViewModelImpl
import kotlinx.android.synthetic.main.fragment_country_details.*

class CountryDetailsFragment : Fragment() {

    private val viewModel: CountryDetailsViewModelImpl by viewModels {
        CountryDetailsViewModelFactory(Dependencies.countriesRepository)
    }

    private var listener: CountryDetailsBackClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // TODO: 12.03.2021 bad
        if (context is CountryDetailsBackClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val country: Country = arguments?.getParcelable(PARAM_COUNTRY) ?: return
        updateCountryDetailsInfo(country)
    }

    @SuppressLint("SetTextI18n")
    private fun updateCountryDetailsInfo(country: Country, returnCountryName: String = "Pоссия") {
        country.apply {
            country_title.text = name
            countryRestrictions?.let {
                country_arrivals.text = "$name: по прибитии"
                country_arrivals_isolation_status.text = it.destinationSelfIsolation
                country_departures.text = "$returnCountryName: по возвращении"
                country_departures_isolation_status.text = it.returnSelfIsolation
                restrictions.text = it.destinationRestrictionsCommentary
            }
        }
    }

    interface CountryDetailsBackClickListener {
        fun onCountryDeselected()
    }

    companion object {

        private const val PARAM_COUNTRY = "country"

        fun create(country: Country) = CountryDetailsFragment().also {
            val args = bundleOf(
                PARAM_COUNTRY to country
            )
            it.arguments = args
        }
    }
}
