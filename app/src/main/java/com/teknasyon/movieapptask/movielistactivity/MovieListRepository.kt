package com.teknasyon.movieapptask.movielistactivity

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.teknasyon.movieapptask.MovieApplication
import com.teknasyon.movieapptask.model.response.ListMoviesResponse
import com.teknasyon.movieapptask.utils.Constants
import com.teknasyon.movieapptask.utils.ErrorUtils
import com.teknasyon.movieapptask.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListRepository(val context: Context) {

    val listMoviesResponse = MutableLiveData<ListMoviesResponse>()
    val showProgress = MutableLiveData<Boolean>()
    private var hasOpenedFragmentFirstTime = false

    fun getMoviesList() {
        if (!hasOpenedFragmentFirstTime) {
            showProgress.value = true
            hasOpenedFragmentFirstTime = true
        }
        MovieApplication.restControllerFactory.getMovieFactory()?.getMoviesList(
            Constants.API_KEY, Constants.LANGUAGE, 1
        )?.enqueue(
            object : Callback<ListMoviesResponse> {
                override fun onResponse(
                    call: Call<ListMoviesResponse>,
                    response: Response<ListMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        listMoviesResponse.value = response.body()
                        Logger.debugSuccessLogMessage("Movies list has been received successfully")
                    } else {
                        val apiError = ErrorUtils.parseError(response)
                        Toast.makeText(context, apiError?.message(), Toast.LENGTH_LONG).show()
                        Logger.debugErrorLogMessage(response.message())
                    }
                    showProgress.value = false
                }

                override fun onFailure(call: Call<ListMoviesResponse>, t: Throwable) {
                    Logger.debugErrorLogMessage(t.message.toString())
                    showProgress.value = false
                }

            }
        )
    }


}