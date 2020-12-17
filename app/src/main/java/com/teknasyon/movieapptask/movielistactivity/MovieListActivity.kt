package com.teknasyon.movieapptask.movielistactivity

import android.os.Bundle
import com.teknasyon.movieapptask.BaseActivity
import com.teknasyon.movieapptask.R

class MovieListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)
    }
}