package com.podium.technicalchallenge.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.compose.dashboard.DashboardDestination
import com.podium.technicalchallenge.compose.movie.MovieDetailScreen
import com.podium.technicalchallenge.compose.nav.MovieDestination

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var title by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title
                    )
                }
            )
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "dashboard"
        ) {
            composable("dashboard") {
                title = stringResource(R.string.title_home)
                DashboardDestination(
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    onMovieClicked = { movie ->
                        navController.navigate(MovieDestination.fromEntity(movie))
                    }
                )
            }
            composable<MovieDestination> {
                val destination: MovieDestination = it.toRoute()

                MovieDetailScreen(
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    movie = destination.entity
                )
            }
        }
    }
}