package com.podium.technicalchallenge.compose.dashboard

import android.util.Log
import androidx.lifecycle.*
import com.podium.technicalchallenge.common.*
import com.podium.technicalchallenge.compose.utils.separateByGenre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.flatMap
import kotlin.collections.sortedBy

enum class Sort {
    Title, Rating, Genre
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    var movies: List<MovieEntity> = listOf()

    private val _moviesMap = MutableStateFlow<Map<String, List<MovieEntity>>>(mapOf())
    val moviesMap: StateFlow<Map<String, List<MovieEntity>>> = _moviesMap

    fun getMovies() {
        viewModelScope.launch {
            movies = movieRepository.getMovies()
            Log.d("DashboardViewModel", "movies=$movies")
            _moviesMap.value = mapOf(
                "" to movies
            )
        }
    }

    fun sortBy(sort: Sort) {
        when (sort) {
            Sort.Title -> {
                _moviesMap.value = mapOf(
                    "" to movies.sortedBy { it.title }
                )
            }
            Sort.Rating -> {
                _moviesMap.value = mapOf(
                    "" to movies.sortedByDescending { it.voteAverage }
                )
            }

            Sort.Genre -> {
                _moviesMap.value = movies.separateByGenre
            }
        }
    }
}