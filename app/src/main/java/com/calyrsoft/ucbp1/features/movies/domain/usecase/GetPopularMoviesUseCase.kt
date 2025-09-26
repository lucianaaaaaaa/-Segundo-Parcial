package com.calyrsoft.ucbp1.features.movies.domain.usecase

import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movies.domain.repository.IMovieRepository

class GetPopularMoviesUseCase(
    private val repo: IMovieRepository
) {
    suspend operator fun invoke(): Result<List<MovieModel>> = repo.getPopularMovies()
}
