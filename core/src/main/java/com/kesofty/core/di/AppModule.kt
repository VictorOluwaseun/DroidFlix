package com.kesofty.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.kesofty.core.local.Converters
import com.kesofty.core.local.MovieDao
import com.kesofty.core.local.MovieDatabase
import com.kesofty.core.remote.MovieApi
import com.kesofty.core.util.MOVIE_DB
import com.kesofty.core.util.interceptors.NetworkConnectivityInterceptor
import com.kesofty.core.util.interceptors.NetworkResponseInterceptor
import com.kesofty.core.util.json.GsonParser
import com.kesofty.core.util.json.JsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).addInterceptor(NetworkConnectivityInterceptor(context))
            .addInterceptor(NetworkResponseInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        var INSTANCE: MovieDatabase? = null

        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                app,
                MovieDatabase::class.java,
                MOVIE_DB
            ).addTypeConverter(Converters(GsonParser(Gson())))
                .build()

            INSTANCE = instance

            instance
        }
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao
    }

    @Provides
    @Singleton
    fun provideGson(): JsonParser {
        return GsonParser(Gson())
    }
}