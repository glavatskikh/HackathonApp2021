package com.dreamteam.hackathonapp2021.presentation.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamteam.hackathonapp2021.R
import com.dreamteam.hackathonapp2021.data.remote.retrofit.RetrofitDataSource
import com.dreamteam.hackathonapp2021.di.MovieRepositoryProvider
import com.dreamteam.hackathonapp2021.di.NetworkModule
import com.dreamteam.hackathonapp2021.presentation.features.moviedetails.view.MovieDetailsFragment
import com.dreamteam.hackathonapp2021.presentation.features.movies.view.MoviesListFragment
import com.dreamteam.hackathonapp2021.repository.MovieRepository
import com.dreamteam.hackathonapp2021.repository.MovieRepositoryImpl

class MainActivity : AppCompatActivity(),
                     MoviesListFragment.MoviesListItemClickListener,
                     MovieDetailsFragment.MovieDetailsBackClickListener,
                     MovieRepositoryProvider {

    private val networkModule = NetworkModule()
    private val remoteDataSource = RetrofitDataSource(networkModule.api)
    private val movieRepository = MovieRepositoryImpl(remoteDataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun onMovieSelected(movieId: Int) {
        routeToMovieDetails(movieId)
    }

    override fun onMovieDeselected() {
        routeBack()
    }

    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                MoviesListFragment.create(),
                MoviesListFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MoviesListFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeToMovieDetails(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                MovieDetailsFragment.create(movieId),
                MovieDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MovieDetailsFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository = movieRepository
}