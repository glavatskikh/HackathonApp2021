package com.dreamteam.hackathonapp2021.repository

import com.dreamteam.hackathonapp2021.model.Movie
import com.dreamteam.hackathonapp2021.model.MovieDetails

interface MovieRepository {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}
