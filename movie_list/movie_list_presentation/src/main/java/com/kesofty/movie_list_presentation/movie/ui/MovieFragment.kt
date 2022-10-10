package com.kesofty.movie_list_presentation.movie.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kesofty.core.ui.dataBindings
import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_presentation.R
import com.kesofty.movie_list_presentation.databinding.FragmentMovieBinding
import com.kesofty.movie_list_presentation.databinding.MovieListLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val binding by dataBindings(FragmentMovieBinding::bind)
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        lifecycleScope.launch {
             viewModel.lowF.collectLatest {
                Log.i("MovieFragment", it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest {
                Log.i("MovieFragment", it)
            }
        }
    }


}

@BindingAdapter(value = ["movieItems"])
fun movieItems(recyclerView: RecyclerView, list: List<Movie>?) {
    list ?: return
    if (recyclerView.adapter == null) {
        recyclerView.adapter = MovieListAdapter()
    }
    (recyclerView.adapter as MovieListAdapter).apply {
        submitList(list)
    }
}

class MovieListAdapter() : ListAdapter<Movie, MovieListViewHolder>(MovieDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
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

    fun bind(movie: Movie) {
        binding.movie = movie
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