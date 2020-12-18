package com.teknasyon.movieapptask.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.teknasyon.movieapptask.model.ResultsModel

class MovieDataSourceFactory : DataSource.Factory<Int, ResultsModel>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, ResultsModel> {
        val movieDataSource = MovieDataSource()
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}