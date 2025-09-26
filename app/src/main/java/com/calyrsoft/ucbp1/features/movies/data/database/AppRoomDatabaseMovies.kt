package com.calyrsoft.ucbp1.features.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.calyrsoft.ucbp1.features.movies.data.database.dao.IMovieDao
import com.calyrsoft.ucbp1.features.movies.data.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDatabaseMovies : RoomDatabase() {
    abstract fun movieDao(): IMovieDao
}
