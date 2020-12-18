package com.teknasyon.movieapptask.moviedetailsactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oxcoding.moviemvvm.data.repository.NetworkState
import com.teknasyon.movieapptask.model.response.MovieDetailsResponse

class MovieDetailsViewModel : ViewModel() {

    private val repository = MovieDetailsRepository()
    val showProgress: LiveData<Boolean>
    val movieDetailsResponse: LiveData<MovieDetailsResponse>
    val networkState: LiveData<NetworkState>

    init {
        this.showProgress = repository.showProgress
        this.movieDetailsResponse = repository.movieDetailsResponse
        this.networkState = repository.networkState
    }

    fun getMovieDetails(tvId: Int?) {
        repository.getMovieDetails(tvId)
    }


}