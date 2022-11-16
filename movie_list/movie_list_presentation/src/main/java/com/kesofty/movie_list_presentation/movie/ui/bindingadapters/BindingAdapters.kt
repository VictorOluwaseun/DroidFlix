package com.kesofty.movie_list_presentation.movie.ui.bindingadapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kesofty.core.util.Result
import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_presentation.movie.ui.adapters.MovieListAdapter

@BindingAdapter(value = ["movieItems"])
fun movieItems(recyclerView: RecyclerView, list: Result<List<Movie>>?) {
    list ?: return
    if (recyclerView.adapter == null) {
        recyclerView.adapter = MovieListAdapter { bindMovieClick(it) }
    }
    (recyclerView.adapter as MovieListAdapter).apply {
        if (list is Result.Success) {
            submitList(list.data)
        }
    }
}

@BindingAdapter("srcUrl", "placeholder", requireAll = false)
fun ImageView.bindSrcUrl(
    url: String,
    placeholder: Drawable?,
) {
    val request = Glide.with(this).load(url)
    if (placeholder != null) request.placeholder(placeholder)
    request.into(this)
}


fun bindMovieClick(movie: Movie) {
//    val request = NavDeepLinkRequest.Builder
//        .fromUri("android-app://".toUri())
//        .build()
//    View.findNavController().navigate(request)
    Log.i("Click", movie.toString())
}
