package com.teknasyon.movieapptask.movielistactivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.teknasyon.movieapptask.datasource.MovieDataSourceFactory
import com.teknasyon.movieapptask.model.ResultsModel
import com.teknasyon.movieapptask.utils.Constants

class MovieListViewModel(val context: Context) : ViewModel() {
    val movieDataSourceFactory = MovieDataSourceFactory(context)
    var moviePagedList: LiveData<PagedList<ResultsModel>>
    var liveDataSource: LiveData<PageKeyedDataSource<Int, ResultsModel>>

    init {

        liveDataSource = movieDataSourceFactory.getMovieLiveDataSource()
        val config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setPageSize(Constants.PAGE_SIZE)
            .build()
        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()
    }
}