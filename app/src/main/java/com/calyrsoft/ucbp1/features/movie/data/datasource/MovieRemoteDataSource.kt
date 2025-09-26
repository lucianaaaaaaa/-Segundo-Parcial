package com.calyrsoft.ucbp1.features.movie.data.datasource

import android.util.Log // Importa Log para depuración
import com.calyrsoft.ucbp1.features.movie.data.api.MovieService
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

class MovieRemoteDataSource(
    private val movieService: MovieService,
    private val apiKey: String
) {
    companion object {
        const val TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185"
    }

    suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        try {
            val response = movieService.fetchPopularMovies(apiKey = apiKey)
            return if (response.isSuccessful) {
                val moviePage = response.body()
                if (moviePage != null && moviePage.results.isNotEmpty()) {
                    val movies = moviePage.results.mapNotNull { dto ->

                        if (!dto.pathUrl.isNullOrBlank()) {
                            val fullPathUrl = TMDB_IMAGE_BASE_URL + dto.pathUrl // dto.pathUrl de TMDB ya empieza con "/"
                            Log.d("MovieRemoteDataSource", "Construida URL: $fullPathUrl para película: ${dto.title}")
                            MovieModel(pathUrl = fullPathUrl, title = dto.title)
                        } else {
                            Log.w("MovieRemoteDataSource", "pathUrl está vacío o nulo para la película: ${dto.title}")

                            MovieModel(pathUrl = "", title = dto.title)

                        }
                    }
                    Result.success(movies)
                } else {
                    Log.d("MovieRemoteDataSource", "Respuesta exitosa pero moviePage es null o no hay resultados.")
                    Result.success(emptyList()) // Devuelve lista vacía si no hay resultados
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Sin cuerpo de error"
                Log.e("MovieRemoteDataSource", "Error en la respuesta de la API: ${response.code()} - $errorBody")
                Result.failure(Exception("Error al obtener películas populares: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.e("MovieRemoteDataSource", "Excepción al obtener películas populares", e)
            return Result.failure(e) // Devuelve la excepción capturada
        }
    }
}

