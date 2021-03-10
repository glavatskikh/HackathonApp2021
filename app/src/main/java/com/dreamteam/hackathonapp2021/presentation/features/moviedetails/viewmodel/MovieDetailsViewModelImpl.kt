package com.dreamteam.hackathonapp2021.presentation.features.moviedetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dreamteam.hackathonapp2021.model.MovieDetails
import com.dreamteam.hackathonapp2021.presentation.features.moviedetails.viewmodel.MovieDetailsViewState.MovieLoaded
import com.dreamteam.hackathonapp2021.presentation.features.moviedetails.viewmodel.MovieDetailsViewState.NoMovie
import com.dreamteam.hackathonapp2021.repository.MovieRepository
import kotlinx.coroutines.launch

internal class MovieDetailsViewModelImpl(private val repository: MovieRepository) : MovieDetailsViewModel() {

    override val stateOutput = MutableLiveData<MovieDetailsViewState>()

    fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.loadMovie(movieId)

            handleMovieLoadResult(movie)
        }
    }

    private fun handleMovieLoadResult(movie: MovieDetails?) {
        if (movie != null) {
            stateOutput.postValue(MovieLoaded(movie))
        } else {
            stateOutput.postValue(NoMovie)
        }
    }
}
