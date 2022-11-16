package com.kesofty.movie_list_data.repository

import com.kesofty.core.local.MovieDao
import com.kesofty.core.util.Result
import com.kesofty.movie_list_data.mapper.toMovieEntityList
import com.kesofty.movie_list_data.mapper.toMovieList
import com.kesofty.movie_list_data.remote.MovieListApi
import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: MovieListApi,
    private val dao: MovieDao,
    private val coroutineDispatcher: CoroutineDispatcher
) : MovieRepository {

    companion object {
        const val PAGE_SIZE = 30
    }

    override fun getMovieList(page: Int, pageSize: Int): Flow<List<Movie>> {
        return dao.getMovieList().map { it.toMovieList() }
    }

    override suspend fun getMovieListRemote(page: Int, pageSize: Int): List<Movie> {
        return withContext(coroutineDispatcher) {
            api.getMovieList(page).toMovieList()
        }
    }

    override suspend fun insertMovieList(movieList: List<Movie>) {
        dao.insertMovieList(movieList.toMovieEntityList())
    }
}