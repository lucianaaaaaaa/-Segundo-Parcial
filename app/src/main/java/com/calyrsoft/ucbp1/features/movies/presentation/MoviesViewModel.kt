package com.calyrsoft.ucbp1.features.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movies.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface MoviesUiState {
    data object Idle : MoviesUiState
    data object Loading : MoviesUiState
    data class Success(val movies: List<MovieModel>) : MoviesUiState
    data class Error(val message: String) : MoviesUiState
}

class MoviesViewModel(
    private val getPopularMovies: GetPopularMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MoviesUiState>(MoviesUiState.Idle)
    val state: StateFlow<MoviesUiState> = _state

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MoviesUiState.Loading
            val result = getPopularMovies()
            _state.value = result.fold(
                onSuccess = { MoviesUiState.Success(it) },
                onFailure = { MoviesUiState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
}
