package com.podium.technicalchallenge.entity

data class MovieResponse(
    val data: Movies
)

data class Movies(
    val movies: List<MovieEntity>
)

data class MovieEntity(
    val title: String,
    val releaseDate: String
)
