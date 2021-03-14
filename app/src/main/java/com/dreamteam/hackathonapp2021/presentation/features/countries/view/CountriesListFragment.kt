package com.dreamteam.hackathonapp2021.presentation.features.countries.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.di.Dependencies
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel.CountriesListViewModelImpl
import com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel.CountryListViewModelFactory
import com.javier.filterview.FilterView
import com.javier.filterview.tag.TagGravity
import com.javier.filterview.tag.TagMode
import com.javier.filterview.tag.TagSection
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
        val adapter = CountriesListAdapter { countryData ->
            listener?.onCountrySelected(countryData)
        }
        view.findViewById<RecyclerView>(R.id.recycler_countries).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)
            this.adapter = adapter
            loadDataToAdapter(adapter)
        }
        search_view.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText)
                return true
            }
        })

        val data = arrayOf(
            "нет карантина по прибытии",
            "нет карантина по возвращении",
            "въезд разрешен",
            "въезд частично разрешен",
            "въезд закрыт",
        )

        val tagSection = TagSection.Builder("Tags", 4)
            .setSectionNameColor(R.color.colorAccent)
            .setSelectedColor(R.color.colorAccent)
            .setDeselectedColor(R.color.colorPrimary)
            .setSelectedFontColor(R.color.colorPrimaryDark)
            .setDeselectedFontColor(R.color.colorAccent)
            .setGravity(TagGravity.CENTER)
            .setMode(TagMode.MULTI)
            .setLabels(data)
            .build()
        FilterView.Builder(requireContext())
            .withTitle("Фильтры поиска")
            .setToolbarVisible(true)
            .withTitleColor(R.color.colorAccent)
            .withDivisorColor(R.color.colorAccent)
            .setCloseIconColor(R.color.colorAccent)
            .addSection(tagSection)
            .build()
            .setOnFilterViewResultListener { data -> Log.d("TAG", data.toString()) }
        //show()

    }

    private fun loadDataToAdapter(adapter: CountriesListAdapter) {
        viewModel.countriesOutput.observe(viewLifecycleOwner, { countriesList ->
            adapter.submitList(countriesList)
            adapter.currentList
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
