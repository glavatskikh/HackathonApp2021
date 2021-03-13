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
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.model.Country

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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val countryImage: ImageView = itemView.findViewById(R.id.country_poster)
        private val countryName: TextView = itemView.findViewById(R.id.country_name)

        fun bind(item: Country, onClickCard: (country: Country) -> Unit) {
            countryName.text = item.name
            countryImage.load(R.drawable.temp) {
                crossfade(true)
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