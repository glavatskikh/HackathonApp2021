package com.dreamteam.hackathonapp2021.presentation.features.moviedetails.viewmodel

import com.dreamteam.hackathonapp2021.model.MovieDetails

internal sealed class MovieDetailsViewState {

    data class MovieLoaded(val movie: MovieDetails) : MovieDetailsViewState()

    object NoMovie : MovieDetailsViewState()
}
