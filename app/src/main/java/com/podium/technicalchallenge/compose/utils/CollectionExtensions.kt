package com.podium.technicalchallenge.compose.utils

import com.podium.technicalchallenge.common.MovieEntity

val List<MovieEntity>.separateByGenre: Map<String, List<MovieEntity>>
    get() {
        val map = mutableMapOf<String, MutableList<MovieEntity>>()
        this.forEach { movieEntity ->
            movieEntity.genres.forEach { genre ->
                if (map[genre] == null) {
                    map[genre] = mutableListOf()
                }
                map[genre]?.add(movieEntity)
            }
        }
        return map
    }