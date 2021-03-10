package com.dreamteam.hackathonapp2021.repository

import com.dreamteam.hackathonapp2021.model.Movie
import com.dreamteam.hackathonapp2021.model.MovieDetails

class MovieRepositoryImpl(
    private val remoteDataResource: com.dreamteam.hackathonapp2021.data.remote.RemoteDataSource
): MovieRepository {

    override suspend fun loadMovies(): List<Movie> {
        return remoteDataResource.loadMovies()
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        return remoteDataResource.loadMovie(movieId)
    }
}