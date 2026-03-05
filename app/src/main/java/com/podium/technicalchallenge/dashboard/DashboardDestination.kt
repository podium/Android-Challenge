package com.podium.technicalchallenge.dashboard

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.podium.technicalchallenge.common.AppTheme


@Keep
@Composable
fun DashboardDestination(){
    val viewModel = hiltViewModel<DashboardViewModel>()

    AppTheme {
        DashboardScreen()
    }
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Text("Podium Challenge")
        }
    }
}
