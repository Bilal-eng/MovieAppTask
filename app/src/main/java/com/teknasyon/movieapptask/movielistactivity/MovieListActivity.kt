package com.teknasyon.movieapptask.movielistactivity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)

        viewModelFactory = ViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.moviePagedList.observe(this, {
            movieListAdapter.submitList(it)
            hideLoading()
        })

        movieListAdapter = MovieListAdapter(this, this)
        rv_movies_list.adapter = movieListAdapter

    }

    override fun onMovieItemClick(movieId: Int?) {
        NavigationHelper.getInstance().startMovieDetailsActivity(this, movieId)
    }
}