package com.dreamteam.hackathonapp2021.model

import android.os.Parcelable
import com.dreamteam.hackathonapp2021.data.api.response.Restrictions
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryRestrictions(
    val travelStatus: MasterTravelStatus,
    val destinationSelfIsolation: String,
    val returnSelfIsolation: String,
    val destinationRestrictionsCommentary: String,
    val safetyStatus: CountryRestrictionsDestinationSafetyStatus?
) : Parcelable {

    companion object {
        fun map(restrictions: Restrictions?): CountryRestrictions? {
            restrictions ?: return null
            val safetyStatus =
                CountryRestrictionsDestinationSafetyStatus.map(restrictions.destinationSafetyStatus)
            return CountryRestrictions(
                MasterTravelStatus.valueOf(restrictions.masterTravelStatus),
                restrictions.destinationSelfIsolationTranslation,
                restrictions.returnSelfIsolationTranslation,
                restrictions.destinationRestrictionsCommentaryTranslation,
                safetyStatus
            )
        }
    }
}