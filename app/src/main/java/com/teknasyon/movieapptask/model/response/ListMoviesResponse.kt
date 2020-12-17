package com.teknasyon.movieapptask.model.response

import com.teknasyon.movieapptask.model.ResultsModel

data class ListMoviesResponse(
    val page: Int?,
    val results: List<ResultsModel>?,
    val total_pages: Int?,
    val total_results: Int?
)