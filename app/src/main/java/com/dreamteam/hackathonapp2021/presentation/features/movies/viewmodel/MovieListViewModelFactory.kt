package com.dreamteam.hackathonapp2021.presentation.features.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dreamteam.hackathonapp2021.repository.MovieRepository

class MovieListViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MoviesListViewModelImpl(repository) as T
}
