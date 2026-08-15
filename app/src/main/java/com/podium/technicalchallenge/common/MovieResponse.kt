package com.podium.technicalchallenge.common

import com.podium.technicalchallenge.compose.utils.*
import java.util.Date

data class MovieResponse(
    val data: Movies
)

data class Movies(
    val movies: List<MovieEntity>
)

data class MovieEntity(
    val id: Int,
    val popularity: Float,
    val posterPath: String,
    val title: String,
    val overview: String,
    val voteAverage: Float,
    val voteCount: Int,
    val genres: List<String>,
    val releaseDate: String
) {
    val releaseDateObj: Date?
        get() = releaseDate.dateFromAPIFormat

    val displayDate: String
        get() = releaseDateObj.displayFormat
}
