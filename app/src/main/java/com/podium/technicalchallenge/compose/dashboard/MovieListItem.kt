package com.podium.technicalchallenge.compose.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.common.MovieEntity
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movie: MovieEntity,
    onMovieClicked: (MovieEntity) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = {
            onMovieClicked(movie)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(80.dp),
                model = movie.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Column(
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

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.average_rating),
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = stringResource(
                            R.string.vote_description,
                            String.format("%.1f", movie.voteAverage, Locale.getDefault()),
                            String.format("%,d", movie.voteCount, Locale.getDefault())
                        ),
                        style = MaterialTheme.typography.caption
                    )
                }

                if (movie.genres.isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.genres),
                            style = MaterialTheme.typography.caption.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = movie.genres.joinToString(", "),
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
        }
    }
}