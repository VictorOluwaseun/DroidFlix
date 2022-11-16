package com.kesofty.movie_list_presentation.movie.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kesofty.core.ui.dataBindings
import com.kesofty.movie_list_presentation.R
import com.kesofty.movie_list_presentation.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val binding by dataBindings(FragmentMovieBinding::bind)
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        lifecycleScope.launch {
//             viewModel.lowF.collectLatest {
//                Log.i("MovieFragment", it)
//            }
        }

        lifecycleScope.launchWhenStarted {
//            viewModel.state.collectLatest {
//                Log.i("MovieFragment", it)
//            }
        }
    }


}