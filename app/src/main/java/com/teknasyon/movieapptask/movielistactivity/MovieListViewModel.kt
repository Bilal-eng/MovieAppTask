package com.teknasyon.movieapptask.movielistactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.oxcoding.moviemvvm.data.repository.NetworkState
import com.teknasyon.movieapptask.model.ResultsModel

class MovieListViewModel : ViewModel() {
    private val repository = MovieListRepository()

    val networkState: LiveData<NetworkState> by lazy {
        repository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    val moviePagedList: LiveData<PagedList<ResultsModel>> by lazy {
        repository.fetchLiveMoviePagedList()
    }
}