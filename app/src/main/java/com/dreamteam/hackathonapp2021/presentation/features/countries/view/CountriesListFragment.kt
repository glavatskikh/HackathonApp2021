package com.dreamteam.hackathonapp2021.presentation.features.countries.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.di.Dependencies
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.model.MasterTravelStatus
import com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel.CountriesListViewModelImpl
import com.dreamteam.hackathonapp2021.presentation.features.countries.viewmodel.CountryListViewModelFactory
import com.javier.filterview.FilterView
import com.javier.filterview.tag.TagGravity
import com.javier.filterview.tag.TagMode
import com.javier.filterview.tag.TagSection
import kotlinx.android.synthetic.main.fragment_countries_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        val tagSection = TagSection.Builder("Фильтры поиска", 4)
            .setSectionNameColor(android.R.color.black)
            .setSelectedFontColor(android.R.color.black)
            .setSelectedColor(R.color.toolbar_color_one)
            .setDeselectedColor(R.color.toolbar_color)
            .setDeselectedFontColor(R.color.grey)
            .setGravity(TagGravity.CENTER)
            .setMode(TagMode.MULTI)
            .setLabels(data)
            .build()


        val dialog =
            object : Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    window?.setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT
                    );
                }
            }

        val filterView = FilterView.Builder(requireContext())
            .withTitle("Готово")
            .setToolbarVisible(true)
            .withTitleColor(R.color.grey)
            .setCloseIconColor(R.color.grey)
            .addSection(tagSection)
            .build()

        filterView.setOnFilterCanceled { dialog.cancel() }
        filterView.setOnFilterViewResultListener { data ->
            val jsonObject = data.optJSONObject(0)
            if (jsonObject == null || jsonObject.length() == 0) {
                adapter.filter(MasterTravelStatus.UNKNOWN)
            } else {
                adapter.filter(MasterTravelStatus.MAJOR)
            }

        }
        dialog.setContentView(filterView)

        filters.setOnClickListener { dialog.show() }
    }

    private fun loadDataToAdapter(adapter: CountriesListAdapter) {
        viewModel.countriesOutput.observe(viewLifecycleOwner, { countriesList ->
            adapter.submitList(countriesList)
        })
        viewModel.progressBarVisibleLiveData.observe(viewLifecycleOwner, {
            lottie_view.isVisible = it
        })
    }


    override fun onPause() {
        super.onPause()
        CoroutineScope(Dispatchers.IO).launch {
//            Dependencies.localDataSource.putCountries(adapter.unfilteredList)
        }
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
