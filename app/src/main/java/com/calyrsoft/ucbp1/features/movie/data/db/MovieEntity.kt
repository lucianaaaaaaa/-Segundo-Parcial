package com.calyrsoft.ucbp1.features.movie.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Clave primaria autogenerada
    val remoteId: String, // Para identificar la película de la API (si tienes un ID único de la API)
    val title: String,
    val pathUrl: String,
    val popularityRank: Int // Nuevo campo para mantener el orden de popularidad
)

fun MovieEntity.toDomainModel(): MovieModel {
    return MovieModel(pathUrl = this.pathUrl, title = this.title)
}

fun MovieModel.toEntity(popularityRank: Int, remoteId: String = this.title): MovieEntity { // Asumimos que el título es único si no hay un ID de la API
    return MovieEntity(
        remoteId = remoteId, // O usa un ID único de la API si está disponible
        title = this.title,
        pathUrl = this.pathUrl,
        popularityRank = popularityRank
    )
}
