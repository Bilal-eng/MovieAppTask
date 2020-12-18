package com.teknasyon.movieapptask.datasource

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.paging.PageKeyedDataSource
import com.teknasyon.movieapptask.MovieApplication
import com.teknasyon.movieapptask.model.ResultsModel
import com.teknasyon.movieapptask.model.response.ListMoviesResponse
import com.teknasyon.movieapptask.utils.CommonUtils
import com.teknasyon.movieapptask.utils.CommonUtils.showProgressBar
import com.teknasyon.movieapptask.utils.Constants
import com.teknasyon.movieapptask.utils.Logger
import com.teknasyon.movieapptask.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource(val context: Context) : PageKeyedDataSource<Int, ResultsModel>() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultsModel>
    ) {
        if (NetworkUtils.isNetworkConnected(context)) {
            getMovies(callback)
        } else {
            Toast.makeText(context, "No network connection", Toast.LENGTH_LONG).show()
        }
    }

    private fun getMovies(callback: LoadInitialCallback<Int, ResultsModel>) {
        context.showProgressBar()
        MovieApplication.restControllerFactory.getMovieFactory()
            ?.getMoviesList(Constants.API_KEY, Constants.LANGUAGE, 1)?.enqueue(
                object : Callback<ListMoviesResponse> {
                    override fun onResponse(
                        call: Call<ListMoviesResponse>,
                        response: Response<ListMoviesResponse>
                    ) {
                        val movieResponse = response.body()
                        val listOfMovies = movieResponse?.results
                        listOfMovies?.let { callback.onResult(it, null, 2) }
                        CommonUtils.hideProgressBar()
                    }

                    override fun onFailure(call: Call<ListMoviesResponse>, t: Throwable) {
                        Logger.debugErrorLogMessage(t.message.toString())
                        CommonUtils.hideProgressBar()
                    }

                }
            )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsModel>) {

        if (NetworkUtils.isNetworkConnected(context)) {
            val previousPageNo = if (params.key > 1) params.key - 1 else 0
            getMoreMovies(params.key, previousPageNo, callback)
        } else {
            Toast.makeText(context, "No network connection", Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsModel>) {
        if (NetworkUtils.isNetworkConnected(context)) {
            val nextPageNo = params.key + 1
            getMoreMovies(params.key, nextPageNo, callback)
        } else {
            Toast.makeText(context, "No network connection", Toast.LENGTH_LONG).show()
        }
    }

    private fun getMoreMovies(
        key: Int?,
        nextPageNo: Int,
        callback: LoadCallback<Int, ResultsModel>
    ) {
        context.showProgressBar()
        MovieApplication.restControllerFactory.getMovieFactory()
            ?.getMoviesList(Constants.API_KEY, Constants.LANGUAGE, key)?.enqueue(
                object : Callback<ListMoviesResponse> {
                    override fun onResponse(
                        call: Call<ListMoviesResponse>,
                        response: Response<ListMoviesResponse>
                    ) {
                        val movieResponse = response.body()
                        val listOfMovies = movieResponse?.results
                        listOfMovies?.let { callback.onResult(it, nextPageNo) }
                        CommonUtils.hideProgressBar()
                    }

                    override fun onFailure(call: Call<ListMoviesResponse>, t: Throwable) {
                        Logger.debugErrorLogMessage(t.message.toString())
                        CommonUtils.hideProgressBar()
                    }

                }
            )

    }


}