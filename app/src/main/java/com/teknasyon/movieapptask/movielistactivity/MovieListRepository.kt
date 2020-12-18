package com.teknasyon.movieapptask.movielistactivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.oxcoding.moviemvvm.data.repository.NetworkState
import com.teknasyon.movieapptask.datasource.MovieDataSource
import com.teknasyon.movieapptask.datasource.MovieDataSourceFactory
import com.teknasyon.movieapptask.model.ResultsModel
import com.teknasyon.movieapptask.utils.Constants

class MovieListRepository(val context: Context) {

    lateinit var movieDataSourceFactory : MovieDataSourceFactory
    lateinit var moviePagedList: LiveData<PagedList<ResultsModel>>

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            movieDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }

    fun fetchLiveMoviePagedList(): LiveData<PagedList<ResultsModel>> {
        movieDataSourceFactory = MovieDataSourceFactory(context)
        val config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setPageSize(Constants.POST_PER_PAGE)
            .build()
        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()

        return moviePagedList
    }

}