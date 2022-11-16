package com.kesofty.movie_list_data.mapper

import com.kesofty.core.local.entity.MovieEntity
import com.kesofty.movie_list_domain.model.Movie

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id,
        adult,
        backdropPath,
        genreIds,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun List<Movie>.toMovieEntityList(): List<MovieEntity> {
    return mapNotNull { it.toMovieEntity() }
}

fun MovieEntity.toMovie(): Movie {
    return Movie(adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount)
}

fun List<MovieEntity>.toMovieList(): List<Movie> {
    return map { it.toMovie() }
}