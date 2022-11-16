package com.kesofty.movie_list_domain.use_case

import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_domain.repository.MovieRepository

class CacheMovie(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movie: Movie){
//        repository.insertMovieList(movie)
    }
}