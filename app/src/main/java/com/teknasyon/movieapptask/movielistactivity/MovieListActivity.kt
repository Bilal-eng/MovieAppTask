package com.teknasyon.movieapptask.movielistactivity

import android.os.Build
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.oxcoding.moviemvvm.data.repository.NetworkState
import com.teknasyon.movieapptask.BaseActivity
import com.teknasyon.movieapptask.R
import com.teknasyon.movieapptask.adapters.MovieListAdapter
import com.teknasyon.movieapptask.adapters.MovieListInterface
import com.teknasyon.movieapptask.utils.NavigationHelper
import com.teknasyon.movieapptask.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_list_movie.*

class MovieListActivity : BaseActivity(), MovieListInterface {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)

        viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.moviePagedList.observe(this, {
            movieListAdapter.submitList(it)
        })


        viewModel.networkState.observe(this, {
            progress_bar.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) VISIBLE else GONE
            tv_error_message.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.ERROR) VISIBLE else GONE

            if (!viewModel.listIsEmpty()) {
                movieListAdapter.setNetworkState(it)
            }
        })

        movieListAdapter = MovieListAdapter(this, this)
        rv_movies_list.adapter = movieListAdapter

    }

    override fun onMovieItemClick(movieId: Int?) {
        NavigationHelper.getInstance().startMovieDetailsActivity(this, movieId)
    }
}