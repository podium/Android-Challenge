package com.podium.technicalchallenge.compose.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.podium.technicalchallenge.common.AppTheme
import com.podium.technicalchallenge.common.MovieEntity


@Composable
fun DashboardDestination(){
    val viewModel = hiltViewModel<DashboardViewModel>()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMovies()
    }

    AppTheme {
        DashboardScreen(
            movies = viewModel.movies.collectAsState().value
        )
    }
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    movies: List<MovieEntity>
) {
    Surface(modifier = modifier.fillMaxSize()) {
        LazyColumn() {
            items(movies) { movie ->
                MovieListItem(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    movie = movie
                )
            }
        }
    }
}
