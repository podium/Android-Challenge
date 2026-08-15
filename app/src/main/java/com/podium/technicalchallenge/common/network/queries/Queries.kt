package com.podium.technicalchallenge.common.network.queries

object Queries {
    fun getMoviesQuery() =
"""
    query GetMoviesQuery {
  movies {
    id
    popularity
    posterPath
    title
    overview
    voteAverage
    voteCount
    genres
    releaseDate
  }
}
"""
}
