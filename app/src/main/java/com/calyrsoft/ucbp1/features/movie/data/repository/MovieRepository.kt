package com.calyrsoft.ucbp1.features.movie.data.repository

import android.util.Log
import com.calyrsoft.ucbp1.features.movie.data.datasource.MovieRemoteDataSource
import com.calyrsoft.ucbp1.features.movie.data.db.MovieDao
import com.calyrsoft.ucbp1.features.movie.data.db.toDomainModel
import com.calyrsoft.ucbp1.features.movie.data.db.toEntity
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.repository.IMoviesRepository

class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: MovieDao
) : IMoviesRepository {

    override suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        // 1. Intenta obtener de la API
        val apiResult = movieRemoteDataSource.fetchPopularMovies()

        return if (apiResult.isSuccess) {
            val moviesFromApi = apiResult.getOrNull()
            if (moviesFromApi != null) {
                // 2. Si la API tiene éxito y devuelve datos, guárdalos/actualízalos en la BD
                try {
                    // Mapea los MovieModel a MovieEntity, asignando el índice como popularityRank
                    val movieEntities = moviesFromApi.mapIndexed { index, movieModel ->
                        // Asegúrate de que tu MovieDto tiene un campo de ID o usa el título como fallback
                        // Aquí asumimos que el título es un identificador si no hay un ID de la API.
                        // Si tu MovieDto tiene un 'id' o 'apiId', úsalo para 'remoteId'.
                        movieModel.toEntity(popularityRank = index, remoteId = movieModel.title /* o dto.id */)
                    }
                    // Reemplaza los datos antiguos con los nuevos
                    movieDao.replacePopularMovies(movieEntities)
                    Log.d("MovieRepository", "Películas de la API guardadas en la BD local.")
                } catch (e: Exception) {
                    Log.e("MovieRepository", "Error al guardar películas en la BD", e)
                    // No falles toda la operación si solo falla el guardado en BD,
                    // aún puedes devolver los datos de la API.
                    // Podrías decidir devolver un error aquí si el guardado en BD es crítico.
                }
            }
            apiResult // Devuelve el resultado de la API
        } else {
            // 3. Si la API falla, intenta cargar desde la BD local
            Log.w("MovieRepository", "Fallo al obtener de la API, intentando cargar desde la BD local.")
            try {
                val moviesFromDb = movieDao.getPopularMovies().map { it.toDomainModel() }
                if (moviesFromDb.isNotEmpty()) {
                    Log.d("MovieRepository", "Películas cargadas desde la BD local.")
                    Result.success(moviesFromDb)
                } else {
                    Log.d("MovieRepository", "No hay películas en la BD local y la API falló.")
                    // Devuelve el error original de la API si la BD también está vacía
                    apiResult
                }
            } catch (e: Exception) {
                Log.e("MovieRepository", "Error al cargar películas desde la BD", e)
                // Si la BD también falla, devuelve el error original de la API
                apiResult
            }
        }
    }
}
