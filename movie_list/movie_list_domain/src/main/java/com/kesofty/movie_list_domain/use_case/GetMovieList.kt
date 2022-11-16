package com.kesofty.movie_list_domain.use_case

import com.kesofty.core.util.Result
import com.kesofty.core.util.networkBoundResource
import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieList(private val repository: MovieRepository) {

    suspend operator fun invoke(
        page: Int = 1,
        pageSize: Int = 40
    ): Flow<Result<List<Movie>>> {

        return networkBoundResource (
            query = { repository.getMovieList(page, pageSize) },
            fetch = { repository.getMovieListRemote(page, pageSize) },
            saveFetchResult = { movies ->
                repository.insertMovieList(movies)
            },
            shouldFetch = { cachedMovies -> cachedMovies.isEmpty() },
            onFetchFailed = {}
        )

//        return repository.getMovieList(page, pageSize)
    }
}