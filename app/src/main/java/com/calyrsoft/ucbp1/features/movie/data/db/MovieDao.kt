package com.calyrsoft.ucbp1.features.movie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies ORDER BY popularityRank ASC") // Ordenar por el nuevo campo
    suspend fun getPopularMovies(): List<MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    // Transacción para borrar e insertar (asegura atomicidad)
    @Transaction
    suspend fun replacePopularMovies(movies: List<MovieEntity>) {
        deleteAllMovies()
        insertMovies(movies)
    }
}
