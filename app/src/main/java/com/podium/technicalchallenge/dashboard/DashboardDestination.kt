package com.podium.technicalchallenge.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel



@Composable
fun DashboardDestination(){
    val viewModel = hiltViewModel<DashboardViewModel>()
    DashboardScreen()
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Podium Challenge")
    }
}