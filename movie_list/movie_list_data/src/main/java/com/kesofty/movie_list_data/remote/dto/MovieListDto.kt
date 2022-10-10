package com.kesofty.movie_list_data.remote.dto

data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val totalPages: Int,
    val totalResults: Int
)