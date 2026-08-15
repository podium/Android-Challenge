package com.podium.technicalchallenge.compose.nav

import com.podium.technicalchallenge.common.MovieEntity
import kotlinx.serialization.Serializable

@Serializable
data class MovieDestination(
    val id: Int,
    val title: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String
) {
    val entity: MovieEntity
        get() = MovieEntity(
            id = id,
            title = title,
            releaseDate = releaseDate,
            popularity = popularity,
            posterPath = posterPath
        )

    companion object {
        fun fromEntity(movie: MovieEntity): MovieDestination {
            return MovieDestination(
                id = movie.id,
                title = movie.title,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate
            )
        }
    }
}