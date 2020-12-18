package com.teknasyon.movieapptask.moviedetailsactivity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.teknasyon.movieapptask.BaseActivity
import com.teknasyon.movieapptask.R
import com.teknasyon.movieapptask.model.response.MovieDetailsResponse
import com.teknasyon.movieapptask.utils.Constants
import com.teknasyon.movieapptask.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieDetailsViewModel
    private var tvId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        viewModelFactory = ViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)

        viewModel.showProgress.observe(this, {
            if (it)
                showLoading()
            else
                hideLoading()
        })

        tvId = intent.getIntExtra("tvId", 5)

        viewModel.getMovieDetails(tvId)

        viewModel.movieDetailsResponse.observe(this, {
            bindViews(it)
        })

    }

    private fun bindViews(movie: MovieDetailsResponse?) {
        val imageUrl = Constants.POSTER_PATH + movie?.poster_path
        Picasso.get().load(imageUrl).fit().into(iv_poster)
        tv_movie_name.text = movie?.name
        tv_vote_average.text = movie?.vote_average.toString()
        tv_vote_count.text = movie?.vote_count.toString()
        tv_overview.text = movie?.overview
        if (movie?.created_by?.size != 0) {
            tv_created_by_value.text = movie?.created_by?.get(0)?.name ?: ""
        }

    }
}