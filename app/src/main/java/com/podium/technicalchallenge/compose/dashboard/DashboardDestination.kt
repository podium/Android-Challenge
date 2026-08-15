package com.podium.technicalchallenge.compose.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.common.*

@Composable
fun DashboardDestination(
    modifier: Modifier = Modifier,
    onMovieClicked: (MovieEntity) -> Unit
){
    val viewModel = hiltViewModel<DashboardViewModel>()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMovies()
    }

    AppTheme {
        DashboardScreen(
            modifier = modifier,
            movies = viewModel.movies.collectAsState().value,
            onMovieClicked = onMovieClicked,
            onSortBy = { sort ->
                viewModel.sortBy(sort)
            }
        )
    }
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    movies: List<MovieEntity>,
    onMovieClicked: (MovieEntity) -> Unit,
    onSortBy: (Sort) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.sort_by),
            style = MaterialTheme.typography.body1
        )
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    onSortBy(Sort.Title)
                }
            ) {
                Text(
                    text = stringResource(R.string.title)
                )
            }

            Button(
                onClick = {
                    onSortBy(Sort.Rating)
                }
            ) {
                Text(
                    text = stringResource(R.string.rating)
                )
            }

            Button(
                onClick = {
                    onSortBy(Sort.Genre)
                }
            ) {
                Text(
                    text = stringResource(R.string.genres)
                )
            }
        }

        LazyColumn {
            items(movies) { movie ->
                MovieListItem(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    movie = movie,
                    onMovieClicked = onMovieClicked
                )
            }
        }
    }
}
