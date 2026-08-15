package com.podium.technicalchallenge.compose.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.podium.technicalchallenge.common.MovieEntity

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movie: MovieEntity
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.h2
        )
    }
}