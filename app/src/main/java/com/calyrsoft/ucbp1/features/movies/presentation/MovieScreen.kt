package com.calyrsoft.ucbp1.features.movies.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    vm: MoviesViewModel = koinViewModel(),
    onBack: () -> Unit = {}
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) { vm.load() }

    // TopAppBar siempre visible
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Películas") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("← Atrás") }
                }
            )
        }
    ) { padding ->
        when (val s = state) {
            is MoviesUiState.Loading, MoviesUiState.Idle -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is MoviesUiState.Error -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: ${s.message}")
                }
            }
            is MoviesUiState.Success -> {
                MoviesGrid(
                    movies = s.movies,
                    modifier = modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
private fun MoviesGrid(movies: List<MovieModel>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies, key = { it.id }) { movie ->
            MovieCard(movie)
        }
    }
}

@Composable
private fun MovieCard(movie: MovieModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f) // cartel estándar
        )
        Text(
            movie.title,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        )
    }
}
