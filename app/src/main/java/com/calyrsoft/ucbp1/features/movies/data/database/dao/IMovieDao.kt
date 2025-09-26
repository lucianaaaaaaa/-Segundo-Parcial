package com.calyrsoft.ucbp1.features.movies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.calyrsoft.ucbp1.features.movies.data.database.entity.MovieEntity

@Dao
interface IMovieDao {
    @Query("SELECT * FROM movies ORDER BY id")
    suspend fun getAll(): List<MovieEntity>

    @Upsert
    suspend fun upsertAll(items: List<MovieEntity>)

}
