package com.podium.technicalchallenge.compose.nav

import com.podium.technicalchallenge.common.MovieEntity
import kotlinx.serialization.Serializable

@Serializable
data class MovieDestination(
    val id: Int,
    val overview: String,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int,
    val popularity: Float,
    val posterPath: String,
    val genres: List<String>,
    val releaseDate: String
) {
    val entity: MovieEntity
        get() = MovieEntity(
            id = id,
            title = title,
            releaseDate = releaseDate,
            popularity = popularity,
            posterPath = posterPath,
            overview = overview,
            voteAverage = voteAverage,
            genres = genres,
            voteCount = voteCount
        )

    companion object {
        fun fromEntity(movie: MovieEntity): MovieDestination {
            return MovieDestination(
                id = movie.id,
                title = movie.title,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                overview = movie.overview,
                voteAverage = movie.voteAverage,
                genres = movie.genres,
                voteCount = movie.voteCount
            )
        }
    }
}