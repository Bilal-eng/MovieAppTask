package com.teknasyon.movieapptask.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.teknasyon.movieapptask.model.ResultsModel

class MovieDataSourceFactory(val context: Context) : DataSource.Factory<Int, ResultsModel>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, ResultsModel> {
        val movieDataSource = MovieDataSource(context)
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}