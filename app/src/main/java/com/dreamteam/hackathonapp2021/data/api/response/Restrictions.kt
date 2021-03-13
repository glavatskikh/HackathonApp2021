package com.dreamteam.hackathonapp2021.data.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Restrictions(
    @SerialName("destination_restrictions_commentary")
    val destinationRestrictionsCommentary: String,
    @SerialName("destination_restrictions_commentary_translation")
    val destinationRestrictionsCommentaryTranslation: String,
    @SerialName("destination_safety_status")
    val destinationSafetyStatus: DestinationSafetyStatus?,
    @SerialName("destination_self_isolation")
    val destinationSelfIsolation: String,
    @SerialName("destination_self_isolation_translation")
    val destinationSelfIsolationTranslation: String,
    @SerialName("entry_restrictions")
    val entryRestrictions: String,
    @SerialName("entry_restrictions_translation")
    val entryRestrictionsTranslation: String,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("master_travel_restrictions_translation")
    val masterTravelRestrictionsTranslation: String,
    @SerialName("master_travel_status")
    val masterTravelStatus: String,
    @SerialName("return_self_isolation")
    val returnSelfIsolation: String,
    @SerialName("return_self_isolation_translation")
    val returnSelfIsolationTranslation: String,
    @SerialName("updated_restrictions")
    val updatedRestrictions: List<String>
)