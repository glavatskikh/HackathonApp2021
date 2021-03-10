package com.dreamteam.hackathonapp2021.presentation.features.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dreamteam.hackathonapp2021.model.Movie

internal abstract class MoviesListViewModel : ViewModel() {

    abstract val moviesOutput: LiveData<List<Movie>>
}
