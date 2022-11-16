package com.kesofty.movie_list_domain.repository

import com.kesofty.core.util.Result
import com.kesofty.movie_list_domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList(
        page: Int,
        pageSize: Int
    ): Flow<List<Movie>>

    suspend fun getMovieListRemote( page: Int,
                            pageSize: Int): List<Movie>

    suspend fun insertMovieList(movieList: List<Movie>)

}