package com.dreamteam.hackathonapp2021.presentation.features.countrydetails.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.di.CountryRepositoryProvider
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel.CountryDetailsViewModelFactory
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel.CountryDetailsViewModelImpl

class CountryDetailsFragment : Fragment() {

    private val viewModel: CountryDetailsViewModelImpl by viewModels {
        CountryDetailsViewModelFactory((requireActivity() as CountryRepositoryProvider).provideCountryRepository())
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

    interface CountryDetailsBackClickListener {
        fun onCountryDeselected()
    }

    companion object {

        private const val PARAM_COUNTRY_ID = "country_id"

        fun create(countryId: Long) = CountryDetailsFragment().also {
            val args = bundleOf(
                PARAM_COUNTRY_ID to countryId
            )
            it.arguments = args
        }
    }
}
