package com.podium.technicalchallenge.compose.nav

import com.podium.technicalchallenge.common.MovieEntity
import kotlinx.serialization.Serializable

@Serializable
data class MovieDestination(
    val title: String,
    val releaseDate: String
) {
    val entity: MovieEntity
        get() = MovieEntity(
            title = title,
            releaseDate = releaseDate
        )

    companion object {
        fun fromEntity(movie: MovieEntity): MovieDestination {
            return MovieDestination(
                title = movie.title,
                releaseDate = movie.releaseDate
            )
        }
    }
}