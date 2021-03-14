package com.dreamteam.hackathonapp2021.model

import android.os.Parcelable
import com.dreamteam.hackathonapp2021.data.api.response.Centroid
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoordinatesCountry(
    val lat: Double,
    val lng: Double
) : Parcelable {
    companion object {
        fun map(centroid: Centroid): CoordinatesCountry {
            return CoordinatesCountry(
                centroid.lat,
                centroid.lng
            )
        }
    }
}