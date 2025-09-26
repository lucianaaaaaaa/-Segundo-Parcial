package com.calyrsoft.ucbp1.features.movies.data.datasource

import com.calyrsoft.ucbp1.features.movies.data.api.MovieService
import com.calyrsoft.ucbp1.features.movies.data.api.dto.MoviePageDto

class MovieRemoteDataSource(
    private val service: MovieService,
    private val apiKey: String
) {
    suspend fun getPopularMovies(): Result<MoviePageDto> = try {
        val resp = service.fetchPopularMovies(apiKey = apiKey)
        if (resp.isSuccessful && resp.body() != null) {
            Result.success(resp.body()!!)
        } else {
            Result.failure(IllegalStateException("HTTP ${resp.code()}: ${resp.message()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
