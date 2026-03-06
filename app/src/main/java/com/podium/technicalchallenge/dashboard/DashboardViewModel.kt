package com.podium.technicalchallenge.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.common.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies() {
        viewModelScope.launch {
            val movies = movieRepository.getMovies()
            Log.d("DashboardViewModel", "movies=$movies")
        }
    }
}