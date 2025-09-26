package com.calyrsoft.ucbp1.features.movies.data.api.dto

data class MoviePageDto(
    val page: Int,
    val results: List<MovieDto>
)
