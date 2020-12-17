package com.teknasyon.movieapptask.connection.factory

import com.teknasyon.movieapptask.model.response.ListMoviesResponse
import com.teknasyon.movieapptask.model.response.MovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieFactory {

    @GET("popular")
    fun getMoviesList(
        @Query("api_key") api_key: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Call<ListMoviesResponse>

    @GET("{tv_id}")
    fun getMovieDetails(
        @Path("tv_id") tv_id: Int?,
        @Query("api_key") api_key: String?,
        @Query("language") language: String?
    ): Call<MovieDetailsResponse>

}