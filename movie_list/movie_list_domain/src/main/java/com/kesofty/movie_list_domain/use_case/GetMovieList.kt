package com.kesofty.movie_list_domain.use_case

import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_domain.repository.MovieRepository

class GetMovieList(private val repository: MovieRepository) {

    suspend operator fun invoke(
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<Movie>> {
        return repository.getMovieList(page, pageSize)
    }
}