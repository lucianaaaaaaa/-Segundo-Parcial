package com.calyrsoft.ucbp1.features.movies.data.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val posterUrl: String
)
