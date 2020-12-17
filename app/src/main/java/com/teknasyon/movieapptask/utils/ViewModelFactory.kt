package com.teknasyon.movieapptask.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teknasyon.movieapptask.MovieApplication
import com.teknasyon.movieapptask.moviedetailsactivity.MovieDetailsViewModel
import com.teknasyon.movieapptask.movielistactivity.MovieListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val application: MovieApplication) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(application) as T
        }

        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}