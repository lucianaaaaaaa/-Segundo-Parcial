package com.calyrsoft.ucbp1.features.movies.data.api.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Long,
    val title: String,
    @SerializedName("poster_path") val posterPath: String?
)
