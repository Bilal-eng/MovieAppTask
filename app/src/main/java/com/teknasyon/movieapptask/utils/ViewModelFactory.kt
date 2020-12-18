package com.teknasyon.movieapptask.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teknasyon.movieapptask.moviedetailsactivity.MovieDetailsViewModel
import com.teknasyon.movieapptask.movielistactivity.MovieListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel() as T
        }

        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}