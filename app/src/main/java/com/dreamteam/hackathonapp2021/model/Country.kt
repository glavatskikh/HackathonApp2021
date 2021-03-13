package com.dreamteam.hackathonapp2021.model

import android.os.Parcelable
import com.dreamteam.hackathonapp2021.data.api.response.Properties
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val id: Long,
    val name: String,
    val code: String,
    val countryRestrictions: CountryRestrictions? = null
) : Parcelable {
    companion object {
        fun map(properties: Properties): Country {
            val countryRestrictions = CountryRestrictions.map(properties.restrictions)
            return Country(
                properties.countryId.toLong(),
                properties.countryName,
                properties.countryCode,
                countryRestrictions
            )
        }
    }
}