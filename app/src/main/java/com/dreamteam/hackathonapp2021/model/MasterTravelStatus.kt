package com.dreamteam.hackathonapp2021.model

import com.dreamteam.hackathonapp2021.R

enum class MasterTravelStatus(val status: String, val statusColor: Int) {
    UNKNOWN("Ограничения неизвестны", R.color.grey),
    MAJOR("Сильные ограничения", android.R.color.holo_red_dark),
    MODERATE("Умеренные ограничения", android.R.color.holo_orange_dark)
}