package com.teknasyon.movieapptask.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teknasyon.movieapptask.MovieApplication
import com.teknasyon.movieapptask.moviedetailsactivity.MovieDetailsViewModel
import com.teknasyon.movieapptask.movielistactivity.MovieListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(context) as T
        }

        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(context) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}