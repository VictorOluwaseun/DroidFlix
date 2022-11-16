package com.kesofty.movie_list_data.di

import com.kesofty.core.di.IoDispatcher
import com.kesofty.core.local.MovieDao
import com.kesofty.movie_list_data.remote.MovieListApi
import com.kesofty.movie_list_data.repository.MovieRepositoryImpl
import com.kesofty.movie_list_domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieListModule {

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieListApi {
        return retrofit.create(MovieListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieListApi,
        dao: MovieDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): MovieRepository {
        return MovieRepositoryImpl(api, dao, ioDispatcher)
    }
}