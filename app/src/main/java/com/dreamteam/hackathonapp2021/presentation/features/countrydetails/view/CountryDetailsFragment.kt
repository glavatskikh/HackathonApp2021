package com.dreamteam.hackathonapp2021.presentation.features.countrydetails.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.model.Country
import com.dreamteam.hackathonapp2021.model.weather.Temperature
import com.dreamteam.hackathonapp2021.model.weather.Weather
import com.dreamteam.hackathonapp2021.model.weather.Wind
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel.CountryDetailsViewModel
import com.dreamteam.hackathonapp2021.presentation.features.countrydetails.viewmodel.CountryDetailsViewModelFactory
import kotlinx.android.synthetic.main.fragment_country_details.*

class CountryDetailsFragment : Fragment() {

    private val viewModel: CountryDetailsViewModel by viewModels {
        CountryDetailsViewModelFactory()
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
        /*country.coordinate?.let { viewModel.getWeather(it.lat, country.coordinate.lng) }*/
        if (country.coordinate != null) {
            viewModel.getWeather(country.coordinate.lat, country.coordinate.lng)
            viewModel.weatherData.observe(viewLifecycleOwner, { setWeather(it) })
            viewModel.temperatureData.observe(viewLifecycleOwner, { setTemperature(it) })
            viewModel.windData.observe(viewLifecycleOwner, { setInfoByWindy(it) })
        } else {
            cardView.isVisible = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateCountryDetailsInfo(country: Country, returnCountryName: String = "Pоссия") {
        country.apply {
            country_title.text = name
            countryRestrictions?.let {
                country_status.text = it.travelStatus.status
                country_status.setTextColor(resources.getColor(it.travelStatus.statusColor))
                country_arrivals.text = "$name: по прибитии"
                country_arrivals_isolation_status.text = it.destinationSelfIsolation
                country_departures.text = "$returnCountryName: по возвращении"
                country_departures_isolation_status.text = it.returnSelfIsolation
                restrictions.text = it.destinationRestrictionsCommentary
            }
        }
    }

    private fun setWeather(weather: Weather) {
        weather.apply {
            weather_status.text = description
            when (weather.main) {
                "Clear" -> img_weather.setImageResource(R.drawable.ic_sun)
                "Snow" -> img_weather.setImageResource(R.drawable.ic_snowy)
                "Rain" -> img_weather.setImageResource(R.drawable.ic_rainy)
                "Clouds" -> img_weather.setImageResource(R.drawable.ic_cloudy)
                else -> img_weather.setImageResource(R.drawable.ic_storm)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTemperature(temperature: Temperature) {
        when {
            temperature.temp > 0.0 -> text_temperature.text = "+${temperature.temp}"
            temperature.temp == 0.0 -> text_temperature.text = temperature.temp.toString()
            temperature.temp < 0.0 -> text_temperature.text = "-${temperature.temp}"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setInfoByWindy(wind: Wind) {
        when (wind.deg) {
            in 31..60 -> wind_status.text = "Ветер СВ ${wind.speed.toInt()} м/с"
            in 61..120 -> wind_status.text = "Ветер В ${wind.speed.toInt()} м/с"
            in 121..150 -> wind_status.text = "Ветер ЮВ ${wind.speed.toInt()} м/с"
            in 151..210 -> wind_status.text = "Ветер Ю ${wind.speed.toInt()} м/с"
            in 211..240 -> wind_status.text = "Ветер ЮЗ ${wind.speed.toInt()} м/с"
            in 241..300 -> wind_status.text = "Ветер З ${wind.speed.toInt()} м/с"
            else -> wind_status.text = "Ветер С ${wind.speed.toInt()} м/с"
        }
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
