package com.podium.technicalchallenge.common

import com.podium.technicalchallenge.compose.utils.dateFromAPIFormat
import com.podium.technicalchallenge.compose.utils.displayFormat
import java.util.Date

data class MovieResponse(
    val data: Movies
)

data class Movies(
    val movies: List<MovieEntity>
)

data class MovieEntity(
    val title: String,
    val releaseDate: String
) {
    val releaseDateObj: Date?
        get() = releaseDate.dateFromAPIFormat

    val displayDate: String
        get() = releaseDateObj.displayFormat
}
