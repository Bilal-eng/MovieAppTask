package com.teknasyon.movieapptask.model.response

import com.teknasyon.movieapptask.model.*

data class MovieDetailsResponse(
    val backdrop_path: String?,
    val created_by: List<CreatedByModel>?,
    val episode_run_time: List<Int>?,
    val first_air_date: String?,
    val genres: List<GenreModel>?,
    val homepage: String?,
    val id: Int?,
    val in_production: Boolean?,
    val languages: List<String>?,
    val last_air_date: String?,
    val last_episode_to_air: LastEpisodeToAirModel?,
    val name: String?,
    val networks: List<NetworkModel>?,
    val next_episode_to_air: Any?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<ProductionCompanyModel>?,
    val production_countries: List<ProductionCountryModel>?,
    val seasons: List<SeasonModel>?,
    val spoken_languages: List<SpokenLanguageModel>?,
    val status: String?,
    val tagline: String?,
    val type: String?,
    val vote_average: Double?,
    val vote_count: Int?
)