package com.podium.technicalchallenge.compose.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
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
            onMovieClicked = onMovieClicked
        )
    }
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    movies: List<MovieEntity>,
    onMovieClicked: (MovieEntity) -> Unit
) {
    Surface(modifier = modifier.fillMaxSize()) {
        LazyColumn() {
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
