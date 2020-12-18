package com.teknasyon.movieapptask.moviedetailsactivity

import androidx.lifecycle.MutableLiveData
import com.oxcoding.moviemvvm.data.repository.NetworkState
import com.teknasyon.movieapptask.MovieApplication
import com.teknasyon.movieapptask.model.response.MovieDetailsResponse
import com.teknasyon.movieapptask.utils.Constants
import com.teknasyon.movieapptask.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsRepository {

    val showProgress = MutableLiveData<Boolean>()
    val movieDetailsResponse = MutableLiveData<MovieDetailsResponse>()
    val networkState = MutableLiveData<NetworkState>()

    fun getMovieDetails(tvId: Int?) {
        showProgress.value = true
        MovieApplication.restControllerFactory.getMovieFactory()?.getMovieDetails(
            tvId,
            Constants.API_KEY, Constants.LANGUAGE
        )?.enqueue(
            object : Callback<MovieDetailsResponse> {
                override fun onResponse(
                    call: Call<MovieDetailsResponse>,
                    response: Response<MovieDetailsResponse>
                ) {
                    if (response.isSuccessful) {
                        movieDetailsResponse.value = response.body()
                        Logger.debugSuccessLogMessage("Movie details has been received successfully")
                        networkState.postValue(NetworkState.LOADED)
                    } else {
                        Logger.debugErrorLogMessage(response.message())
                        networkState.postValue(NetworkState.ERROR)
                    }
                    showProgress.value = false
                }

                override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                    Logger.debugErrorLogMessage(t.message.toString())
                    showProgress.value = false
                    networkState.postValue(NetworkState.ERROR)
                }

            }
        )

    }


}