package com.calyrsoft.ucbp1.features.movies.data.datasource

import com.calyrsoft.ucbp1.features.movies.data.database.dao.IMovieDao
import com.calyrsoft.ucbp1.features.movies.data.database.entity.MovieEntity
import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel

class MovieLocalDataSource(
    private val dao: IMovieDao
) {
    suspend fun saveAll(models: List<MovieModel>) {
        val entities = models.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                posterUrl = it.posterUrl ?: ""
            )
        }
        dao.upsertAll(entities)
    }

    suspend fun getAll(): List<MovieModel> =
        dao.getAll().map { e ->
            MovieModel(
                id = e.id,
                title = e.title,
                posterUrl = e.posterUrl.ifEmpty { null }
            )
        }
}
