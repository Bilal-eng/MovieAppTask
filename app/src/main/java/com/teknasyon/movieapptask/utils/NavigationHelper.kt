package com.teknasyon.movieapptask.utils

import android.content.Context
import android.content.Intent
import com.teknasyon.movieapptask.moviedetailsactivity.MovieDetailsActivity

class NavigationHelper {

    companion object {
        private val instance = NavigationHelper()

        fun getInstance(): NavigationHelper {
            return instance
        }
    }

    fun startMovieDetailsActivity(context: Context, tvId: Int?) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra("tvId", tvId)
        context.startActivity(intent)
    }
}