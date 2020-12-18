package com.teknasyon.movieapptask.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.teknasyon.movieapptask.model.ResultsModel

class MovieDataSourceFactory(val context: Context) : DataSource.Factory<Int, ResultsModel>() {

    private val movieLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, ResultsModel>> =
        MutableLiveData()

    override fun create(): DataSource<Int, ResultsModel> {
        val movieDataSource = MovieDataSource(context)
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }

    fun getMovieLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, ResultsModel>> {
        return movieLiveDataSource
    }
}