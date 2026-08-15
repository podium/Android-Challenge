package com.podium.technicalchallenge.compose.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.common.MovieEntity
import com.podium.technicalchallenge.common.MovieRepository
import com.podium.technicalchallenge.compose.utils.separateByGenre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class Sort {
    Title, Rating, Genre
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieEntity>>(listOf())
    val movies: StateFlow<List<MovieEntity>> = _movies

    private val _moviesByGenre = MutableStateFlow<Map<String, List<MovieEntity>>>(mapOf())
    val moviesByGenre: StateFlow<Map<String, List<MovieEntity>>> = _moviesByGenre

    fun getMovies() {
        viewModelScope.launch {
            val movies = movieRepository.getMovies()
            Log.d("DashboardViewModel", "movies=$movies")
            _movies.value = movies
        }
    }

    fun sortBy(sort: Sort) {
        when (sort) {
            Sort.Title -> {
                _movies.value = movies.value.sortedBy { it.title }
            }
            Sort.Rating -> {
                _movies.value = movies.value.sortedByDescending { it.voteAverage }
            }

            Sort.Genre -> {
                _moviesByGenre.value = movies.value.separateByGenre
            }
        }
    }
}