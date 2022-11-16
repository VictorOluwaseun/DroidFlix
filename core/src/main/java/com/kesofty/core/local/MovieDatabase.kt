package com.kesofty.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kesofty.core.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract val movieDao: MovieDao
}