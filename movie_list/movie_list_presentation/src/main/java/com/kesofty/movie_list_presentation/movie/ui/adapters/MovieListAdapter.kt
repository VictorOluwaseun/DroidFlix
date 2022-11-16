package com.kesofty.movie_list_presentation.movie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_presentation.databinding.MovieListLayoutBinding

class MovieListAdapter(
    private val onMovieClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieListViewHolder>(MovieDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position), onMovieClick)
    }

}

class MovieListViewHolder(
    private val binding: MovieListLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): MovieListViewHolder {
            return MovieListViewHolder(
                MovieListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    fun bind(movie: Movie, onMovieClick: (Movie) -> Unit) {
        binding.movie = movie
        binding.container.setOnClickListener {
            onMovieClick(movie)
        }
    }
}

object MovieDiff : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

interface MovieListener {
    fun onMovieClick(movie: Movie)
}