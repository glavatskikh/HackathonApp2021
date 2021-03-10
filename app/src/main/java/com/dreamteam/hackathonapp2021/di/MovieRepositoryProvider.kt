package com.dreamteam.hackathonapp2021.di

import com.dreamteam.hackathonapp2021.repository.MovieRepository


internal interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}