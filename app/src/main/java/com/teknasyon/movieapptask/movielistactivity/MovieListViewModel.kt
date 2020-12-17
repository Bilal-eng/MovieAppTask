package com.teknasyon.movieapptask.movielistactivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.teknasyon.movieapptask.model.response.ListMoviesResponse

class MovieListViewModel(context: Context) : ViewModel() {

    private val repository = MovieListRepository(context)
    val listMovieResponse: LiveData<ListMoviesResponse>
    val showProgress: LiveData<Boolean>

    init {
        this.listMovieResponse = repository.listMoviesResponse
        this.showProgress = repository.showProgress
    }


    fun getMoviesList() {
        repository.getMoviesList()
    }

}