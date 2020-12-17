package com.teknasyon.movieapptask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.redditclone.utils.DiffUtilCallBack
import com.squareup.picasso.Picasso
import com.teknasyon.movieapptask.R
import com.teknasyon.movieapptask.model.ResultsModel
import com.teknasyon.movieapptask.utils.Constants

class MovieListAdapter(val context: Context?, private val movieListInterface: MovieListInterface?) :
    PagedListAdapter<ResultsModel, MovieListAdapter.ViewHolder>(DiffUtilCallBack()) {

    private var listOfMovies: List<ResultsModel>? = ArrayList()


    fun setListOfMovies(listOfMovies: List<ResultsModel>?) {
        this.listOfMovies = listOfMovies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (listOfMovies != null) {
            return listOfMovies!!.size
        }
        return 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listOfMovies?.get(position)

        val imageUrl = Constants.POSTER_PATH + movie?.poster_path

        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.iv_poster)

        holder.tv_movie_name_value.text = movie?.name

        if (movie?.origin_country?.size != 0)
            holder.tv_country_name.text = movie?.origin_country?.get(0) ?: "US"
        else
            holder.tv_country_name.text = "US"

        holder.tv_popularity_value.text = movie?.popularity.toString()

        holder.tv_date_value.text = movie?.first_air_date

        holder.tv_vote_average_value.text = movie?.vote_average.toString()

        holder.tv_vote_count_value.text = movie?.vote_count.toString()

        holder.itemView.setOnClickListener {
            movieListInterface?.onMovieItemClick(movie?.id)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val iv_poster: ImageView = itemView.findViewById(R.id.iv_poster)
        val tv_movie_name_value: TextView = itemView.findViewById(R.id.tv_movie_name_value)
        val tv_country_name: TextView = itemView.findViewById(R.id.tv_country_name)
        val tv_popularity_value: TextView = itemView.findViewById(R.id.tv_popularity_value)
        val tv_date_value: TextView = itemView.findViewById(R.id.tv_date_value)
        val tv_vote_average_value: TextView = itemView.findViewById(R.id.tv_vote_average_value)
        val tv_vote_count_value: TextView = itemView.findViewById(R.id.tv_vote_count_value)

    }
}