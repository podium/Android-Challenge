package com.podium.technicalchallenge.compose.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.podium.technicalchallenge.common.MovieEntity

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movie: MovieEntity
) {
    Column(
        modifier = modifier
    ) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = movie.posterPath,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Text(
                modifier = Modifier.background(
                    color = MaterialTheme.colors.background.copy(
                        alpha = 0.4f
                    )
                ),
                text = movie.title,
                style = MaterialTheme.typography.h2
            )
        }
    }
}