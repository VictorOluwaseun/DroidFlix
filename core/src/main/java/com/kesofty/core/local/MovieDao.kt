package com.kesofty.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kesofty.core.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM movieEntity")
    fun getMovieList(): Flow<List<MovieEntity>>


}