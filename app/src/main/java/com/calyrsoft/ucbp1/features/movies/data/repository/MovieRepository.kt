package com.calyrsoft.ucbp1.features.movies.data.repository

import com.calyrsoft.ucbp1.features.movies.data.datasource.MovieLocalDataSource
import com.calyrsoft.ucbp1.features.movies.data.datasource.MovieRemoteDataSource
import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movies.domain.repository.IMovieRepository

private const val POSTER_BASE = "https://image.tmdb.org/t/p/w342"

class MovieRepository(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) : IMovieRepository {

    override suspend fun getPopularMovies(): Result<List<MovieModel>> {
        val remoteResult: Result<List<MovieModel>> =
            remote.getPopularMovies().map { page ->
                page.results.map { dto ->
                    MovieModel(
                        id = dto.id,
                        title = dto.title,
                        posterUrl = dto.posterPath?.let { "$POSTER_BASE$it" } ?: ""
                    )
                }
            }

        if (remoteResult.isSuccess) {
            val list = remoteResult.getOrNull().orEmpty()
            local.saveAll(list)
            val fromDb = local.getAll()
            return Result.success(fromDb)
        }


        return remoteResult
    }
}
