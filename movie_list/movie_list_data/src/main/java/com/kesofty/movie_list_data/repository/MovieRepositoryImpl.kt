package com.kesofty.movie_list_data.repository

import com.kesofty.movie_list_data.mapper.toMovieList
import com.kesofty.movie_list_data.remote.MovieListApi
import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: MovieListApi,
    private val coroutineDispatcher: CoroutineDispatcher
) : MovieRepository {
    override suspend fun getMovieList(page: Int, pageSize: Int): Result<List<Movie>> {
        return try {
            withContext(coroutineDispatcher) {
                Result.success(api.getMovieList(1).toMovieList())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    companion object {
        const val PAGE_SIZE = 30
    }
}