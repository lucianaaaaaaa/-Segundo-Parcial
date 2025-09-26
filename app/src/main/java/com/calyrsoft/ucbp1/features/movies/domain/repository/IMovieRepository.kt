package com.calyrsoft.ucbp1.features.movies.domain.repository


import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel

interface IMovieRepository {
    suspend fun getPopularMovies(): Result<List<MovieModel>>
}
