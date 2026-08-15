package com.podium.technicalchallenge.compose.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.podium.technicalchallenge.common.MovieEntity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movie: MovieEntity
) {
    Card(
        modifier = modifier,
        onClick = {}
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h4
            )

            Text(
                text = movie.displayDate,
                style = MaterialTheme.typography.body2
            )
        }
    }
}