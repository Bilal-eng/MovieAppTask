package com.teknasyon.movieapptask.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.oxcoding.moviemvvm.data.repository.NetworkState
import com.teknasyon.movieapptask.MovieApplication
import com.teknasyon.movieapptask.model.ResultsModel
import com.teknasyon.movieapptask.model.response.ListMoviesResponse
import com.teknasyon.movieapptask.utils.Constants
import com.teknasyon.movieapptask.utils.Constants.Companion.FIRST_PAGE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource : PageKeyedDataSource<Int, ResultsModel>() {

    private var page = FIRST_PAGE
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultsModel>
    ) {
        networkState.postValue(NetworkState.LOADING)

        MovieApplication.restControllerFactory.getMovieFactory()
            ?.getMoviesList(Constants.API_KEY, Constants.LANGUAGE, page)?.enqueue(
                object : Callback<ListMoviesResponse> {
                    override fun onResponse(
                        call: Call<ListMoviesResponse>,
                        response: Response<ListMoviesResponse>
                    ) {
                        response.body()?.results?.let { callback.onResult(it, null, page + 1) }
                        networkState.postValue(NetworkState.LOADED)
                    }

                    override fun onFailure(call: Call<ListMoviesResponse>, t: Throwable) {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDataSource", t.message.toString())
                    }

                }
            )
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsModel>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsModel>) {
        networkState.postValue(NetworkState.LOADING)

        MovieApplication.restControllerFactory.getMovieFactory()
            ?.getMoviesList(Constants.API_KEY, Constants.LANGUAGE, params.key)?.enqueue(
                object : Callback<ListMoviesResponse> {
                    override fun onResponse(
                        call: Call<ListMoviesResponse>,
                        response: Response<ListMoviesResponse>
                    ) {
                        if (response.body()?.total_pages != null) {
                            if (response.body()?.total_pages!! >= params.key) {
                                response.body()?.results?.let {
                                    callback.onResult(
                                        it,
                                        params.key + 1
                                    )
                                }
                                networkState.postValue(NetworkState.LOADED)
                            } else {
                                networkState.postValue(NetworkState.ENDOFLIST)
                            }
                        } else {
                            networkState.postValue(NetworkState.ENDOFLIST)
                        }
                    }

                    override fun onFailure(call: Call<ListMoviesResponse>, t: Throwable) {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDataSource", t.message.toString())
                    }

                }
            )
    }

}