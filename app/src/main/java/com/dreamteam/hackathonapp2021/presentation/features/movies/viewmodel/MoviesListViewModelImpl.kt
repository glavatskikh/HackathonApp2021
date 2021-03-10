package com.dreamteam.hackathonapp2021.presentation.features.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dreamteam.hackathonapp2021.model.Movie
import com.dreamteam.hackathonapp2021.repository.MovieRepository
import kotlinx.coroutines.launch

internal class MoviesListViewModelImpl(private val repository: MovieRepository) : MoviesListViewModel() {

    override val moviesOutput = MutableLiveData<List<Movie>>()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            moviesOutput.postValue(repository.loadMovies())
        }
    }
}
