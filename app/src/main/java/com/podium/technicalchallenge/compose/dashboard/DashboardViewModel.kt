package com.podium.technicalchallenge.compose.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.common.MovieEntity
import com.podium.technicalchallenge.common.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieEntity>>(listOf())
    val movies: StateFlow<List<MovieEntity>> = _movies

    fun getMovies() {
        viewModelScope.launch {
            val movies = movieRepository.getMovies()
            Log.d("DashboardViewModel", "movies=$movies")
            _movies.value = movies
        }
    }
}