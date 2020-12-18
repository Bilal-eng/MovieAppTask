package com.teknasyon.movieapptask.movielistactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.oxcoding.moviemvvm.data.repository.NetworkState
import com.teknasyon.movieapptask.datasource.MovieDataSource
import com.teknasyon.movieapptask.datasource.MovieDataSourceFactory
import com.teknasyon.movieapptask.model.ResultsModel
import com.teknasyon.movieapptask.utils.Constants

class MovieListRepository {

    lateinit var movieDataSourceFactory : MovieDataSourceFactory
    lateinit var moviePagedList: LiveData<PagedList<ResultsModel>>

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(
            movieDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }

    fun fetchLiveMoviePagedList(): LiveData<PagedList<ResultsModel>> {
        movieDataSourceFactory = MovieDataSourceFactory()
        val config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setPageSize(Constants.POST_PER_PAGE)
            .build()
        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()

        return moviePagedList
    }

}