package com.teknasyon.movieapptask.movielistactivity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.teknasyon.movieapptask.BaseActivity
import com.teknasyon.movieapptask.R
import com.teknasyon.movieapptask.adapters.MovieListAdapter
import com.teknasyon.movieapptask.adapters.MovieListInterface
import com.teknasyon.movieapptask.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_list_movie.*

class MovieListActivity : BaseActivity(), MovieListInterface {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)


        swipe_to_refresh_movie_list.setColorSchemeResources(R.color.purple_500)

        swipe_to_refresh_movie_list.setOnRefreshListener {
            viewModel.getMoviesList()
        }

        viewModelFactory = ViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.showProgress.observe(this, {
            if (it)
                showLoading()
            else
                hideLoading()
        })

        viewModel.getMoviesList()

        viewModel.listMovieResponse.observe(this, {
            movieListAdapter.setListOfMovies(it.results)
            swipe_to_refresh_movie_list.isRefreshing = false
        })

        movieListAdapter = MovieListAdapter(this, this)
        rv_movies_list.adapter = movieListAdapter

    }

    override fun onMovieItemClick(movieId: Int?) {
        Toast.makeText(this, movieId.toString(), Toast.LENGTH_LONG).show()
    }
}