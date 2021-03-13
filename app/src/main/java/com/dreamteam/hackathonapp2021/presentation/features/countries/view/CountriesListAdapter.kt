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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CountriesListAdapter(private val onClickCard: (country: Country) -> Unit) :
        ListAdapter<Country, CountriesListAdapter.ViewHolder>(DiffCallback()) {


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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scope = CoroutineScope(Dispatchers.Main)

        private val countryImage: ImageView = itemView.findViewById(R.id.country_poster)
        private val countryName: TextView = itemView.findViewById(R.id.country_name)

        fun bind(item: Country, onClickCard: (country: Country) -> Unit) {
            countryName.text = item.name
            countryImage.load(R.drawable.temp) {
                crossfade(true)
            }
            scope.launch {

                val loadUrlPhoto = NetworkModule.apiPhoto.loadCountryPhoto(q = "город+"+item.name)
                loadUrlPhoto.hits.forEach { photo ->
                    Glide.with(countryImage.context)
                            .load(photo.webformatURL)
                            .override(176, 112)
                            .into(countryImage)
                     }

            }

            itemView.setOnClickListener {
                onClickCard(item)
            }
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