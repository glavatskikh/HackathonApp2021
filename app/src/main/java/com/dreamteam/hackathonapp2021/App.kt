package com.dreamteam.hackathonapp2021

import android.app.Application
import com.dreamteam.hackathonapp2021.di.Dependencies

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Dependencies.preferences =
            applicationContext.getSharedPreferences("hackathonapp2021", MODE_PRIVATE)
    }
}