package com.dreamteam.hackathonapp2021.presentation.features.countries.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.data.api.NetworkModule
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.model.MasterTravelStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesListAdapter(private val onClickCard: (country: Country) -> Unit) :
    ListAdapter<Country, CountriesListAdapter.ViewHolder>(DiffCallback()) {

    private val unfilteredList: MutableList<Country> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickCard)
    }

    override fun submitList(list: List<Country>?) {
        if (list != null) {
            unfilteredList.clear()
            unfilteredList.addAll(list)
        }
        super.submitList(list)
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<Country>()
        if (!query.isNullOrEmpty()) {
            list.addAll(unfilteredList.filter {
                it.name.contains(other = query, ignoreCase = true)
            })
        } else {
            list.addAll(unfilteredList)
        }
        super.submitList(list)
    }

    fun filter(query: MasterTravelStatus) {
        val list = mutableListOf<Country>()
        if (query != MasterTravelStatus.UNKNOWN) {
            list.addAll(unfilteredList.filter {
                it.countryRestrictions?.travelStatus == query
            })
        } else {
            list.addAll(unfilteredList)
        }
        super.submitList(list)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryImage: ImageView = itemView.findViewById(R.id.country_poster)
        private val countryName: TextView = itemView.findViewById(R.id.country_name)

        fun bind(item: Country, onClickCard: (country: Country) -> Unit) {
            countryName.text = item.name
            // TODO: 14.03.2021 change scope logic
            scope.launch {
                var imageUrl = item.imageUrl
                if (imageUrl.isNullOrEmpty()) {
                    try {
                        val loadUrlPhoto =
                            NetworkModule.apiPhoto.loadCountryPhoto(query = "город+" + item.name)
                        if (loadUrlPhoto.hits.isNotEmpty()) {
                            val photo = loadUrlPhoto.hits.first()
                            item.imageUrl = photo.webformatURL
                            imageUrl = photo.webformatURL
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                if (imageUrl.isNullOrEmpty()) {
                    countryImage.load(R.drawable.temp) {
                        crossfade(true)
                    }
                } else {
                    Glide.with(countryImage.context)
                        .load(imageUrl)
                        .override(176, 112)
                        .into(countryImage)
                }
            }
            itemView.setOnClickListener {
                onClickCard(item)
            }
        }

        companion object {
            private val scope = CoroutineScope(Dispatchers.Main)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}