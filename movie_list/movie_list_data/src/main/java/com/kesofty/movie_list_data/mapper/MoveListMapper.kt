package com.kesofty.movie_list_data.mapper

import com.kesofty.movie_list_data.remote.dto.MovieDto
import com.kesofty.movie_list_data.remote.dto.MovieListDto
import com.kesofty.movie_list_domain.model.Movie

fun MovieDto.toMovie(): Movie? {
    return Movie(adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount)
}

fun MovieListDto.toMovieList(): List<Movie> {
    return results.mapNotNull {
        it.toMovie()
    }
}