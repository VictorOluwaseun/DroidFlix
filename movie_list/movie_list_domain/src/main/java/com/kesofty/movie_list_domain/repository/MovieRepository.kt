package com.kesofty.movie_list_domain.repository

import com.kesofty.movie_list_domain.model.Movie

interface MovieRepository {

    suspend fun getMovieList(
        page: Int,
        pageSize: Int
    ): Result<List<Movie>>

    suspend fun insertMovie(movie: Movie)

}