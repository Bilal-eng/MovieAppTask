package com.teknasyon.movieapptask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oxcoding.moviemvvm.data.repository.NetworkState
import com.raywenderlich.android.redditclone.utils.DiffUtilCallBack
import com.squareup.picasso.Picasso
import com.teknasyon.movieapptask.R
import com.teknasyon.movieapptask.model.ResultsModel
import com.teknasyon.movieapptask.utils.Constants
import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.android.synthetic.main.network_state_item.view.*

class MovieListAdapter(val context: Context?, private val movieListInterface: MovieListInterface?) :
    PagedListAdapter<ResultsModel, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2
    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        return if (viewType == MOVIE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
            MovieItemViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            NetworkStateItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == MOVIE_VIEW_TYPE) {
            (holder as MovieItemViewHolder).bind(getItem(position), movieListInterface)
        } else {
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }

    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: ResultsModel?, movieListInterface: MovieListInterface?) {

            val imageUrl = Constants.POSTER_PATH + movie?.poster_path

            Picasso.get().load(imageUrl).fit().centerCrop().into(itemView.iv_poster)

            itemView.tv_movie_name_value.text = movie?.name

            if (movie?.origin_country?.size != 0)
                itemView.tv_country_name.text = movie?.origin_country?.get(0) ?: "US"
            else
                itemView.tv_country_name.text = "US"

            itemView.tv_popularity_value.text = movie?.popularity.toString()

            itemView.tv_date_value.text = movie?.first_air_date

            itemView.tv_vote_average_value.text = movie?.vote_average.toString()

            itemView.tv_vote_count_value.text = movie?.vote_count.toString()

            itemView.cardView.setOnClickListener {
                movieListInterface?.onMovieItemClick(movie?.id)
            }

        }

    }

    class NetworkStateItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(networkState: NetworkState?) {
            if (networkState != null && networkState == NetworkState.LOADING) {
                itemView.progress_bar_item.visibility = View.VISIBLE;
            } else {
                itemView.progress_bar_item.visibility = View.GONE;
            }

            if (networkState != null && networkState == NetworkState.ERROR) {
                itemView.error_msg_item.visibility = View.VISIBLE;
                itemView.error_msg_item.text = networkState.msg;
            } else if (networkState != null && networkState == NetworkState.ENDOFLIST) {
                itemView.error_msg_item.visibility = View.VISIBLE;
                itemView.error_msg_item.text = networkState.msg;
            } else {
                itemView.error_msg_item.visibility = View.GONE;
            }
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }

    }
}