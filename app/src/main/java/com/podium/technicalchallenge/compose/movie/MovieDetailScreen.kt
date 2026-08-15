package com.podium.technicalchallenge.compose.movie

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.common.MovieEntity
import java.util.Locale

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movie: MovieEntity
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = movie.posterPath,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = movie.title,
            style = MaterialTheme.typography.h4
        )

        Text(
            text = stringResource(
                R.string.vote_description,
                String.format("%.1f", movie.voteAverage, Locale.getDefault()),
                String.format("%,d", movie.voteCount, Locale.getDefault())
            ),
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = movie.overview,
            style = MaterialTheme.typography.body1
        )
    }
}