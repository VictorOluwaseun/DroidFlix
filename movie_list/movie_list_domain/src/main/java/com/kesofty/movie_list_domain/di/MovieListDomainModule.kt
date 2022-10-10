package com.kesofty.movie_list_domain.di

import com.kesofty.core.di.IoDispatcher
import com.kesofty.movie_list_domain.repository.MovieRepository
import com.kesofty.movie_list_domain.use_case.CacheMovie
import com.kesofty.movie_list_domain.use_case.GetMovieList
import com.kesofty.movie_list_domain.use_case.MovieUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object MovieListDomainModule {

    @ViewModelScoped
    @Provides
    fun provideMovieUseCases(
        repository: MovieRepository,
    ): MovieUseCases {
        return MovieUseCases(
            getMovieList = GetMovieList(repository),
            cacheMovie = CacheMovie(repository)
        )
    }
}