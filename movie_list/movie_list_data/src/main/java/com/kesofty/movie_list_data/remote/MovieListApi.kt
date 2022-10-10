package com.kesofty.movie_list_data.remote

import com.kesofty.core.remote.MovieApi
import com.kesofty.movie_list_data.BuildConfig
import com.kesofty.movie_list_data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListApi : MovieApi {

    @GET("https://api.themoviedb.org/3/movie/top_rated?api_key=${BuildConfig.API_KEY}&page=1&language=en-us")
    suspend fun getMovieList(
    @Query("page") page: Int
    ): MovieListDto

}