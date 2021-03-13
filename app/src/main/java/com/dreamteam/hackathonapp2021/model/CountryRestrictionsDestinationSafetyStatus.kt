package com.dreamteam.hackathonapp2021.model

import android.os.Parcelable
import com.dreamteam.hackathonapp2021.data.api.response.DestinationSafetyStatus
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryRestrictionsDestinationSafetyStatus(
    val epiPrevalenceRecent: Double
) : Parcelable {

    companion object {
        fun map(safetyStatus: DestinationSafetyStatus?): CountryRestrictionsDestinationSafetyStatus? {
            safetyStatus ?: return null
            return CountryRestrictionsDestinationSafetyStatus(
                safetyStatus.epiPrevalenceRecent
            )
        }
    }
}