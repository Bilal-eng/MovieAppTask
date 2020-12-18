package com.teknasyon.movieapptask.moviedetailsactivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.teknasyon.movieapptask.model.response.MovieDetailsResponse

class MovieDetailsViewModel(val context: Context) : ViewModel() {

    private val repository = MovieDetailsRepository(context)
    val showProgress: LiveData<Boolean>
    val movieDetailsResponse: LiveData<MovieDetailsResponse>

    init {
        this.showProgress = repository.showProgress
        this.movieDetailsResponse = repository.movieDetailsResponse
    }

    fun getMovieDetails(tvId: Int?) {
        repository.getMovieDetails(tvId)
    }


}