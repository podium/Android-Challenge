package com.podium.technicalchallenge.common.network.queries

object Queries {
    fun getMoviesQuery() =
"""
    query GetMoviesQuery {
  movies {
    title
    releaseDate
  }
}
"""
}
