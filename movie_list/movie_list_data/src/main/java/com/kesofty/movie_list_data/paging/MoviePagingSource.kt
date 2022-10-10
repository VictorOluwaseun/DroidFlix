package com.kesofty.movie_list_data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kesofty.movie_list_domain.model.Movie

private const val MOVIE_STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val query: String
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: MOVIE_STARTING_PAGE_INDEX
        val apiQuery = query //+ IN_QUALIFIER
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}