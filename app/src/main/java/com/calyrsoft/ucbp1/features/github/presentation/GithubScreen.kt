package com.calyrsoft.ucbp1.features.github.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.calyrsoft.ucbp1.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun GithubScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    vm : GithubViewModel = koinViewModel()
) {
    var nickname by remember { mutableStateOf("") }
    val state by vm.state.collectAsState()

    Column {
        // Buscador de usuario
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("GitHub Nickname") }
        )
        OutlinedButton(onClick = {
            vm.fetchAlias(nickname)
        }) {
            Text("Buscar")
        }

        // Estados de la UI
        when(val st = state) {
            is GithubViewModel.GithubStateUI.Error -> Text(st.message)
            GithubViewModel.GithubStateUI.Init -> Text("Ingrese un usuario de GitHub")
            GithubViewModel.GithubStateUI.Loading -> Text("Cargando...")
            is GithubViewModel.GithubStateUI.Success -> {
                Text(st.github.nickname)
                AsyncImage(
                    model = st.github.pathUrl,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(st.github.pathUrl)
            }
        }

        // Navegación a otras pantallas
        OutlinedButton(onClick = { navController.navigate(Screen.PopularMovies.route) }) {
            Text("Ir a Películas")
        }
        OutlinedButton(onClick = { navController.navigate(Screen.Dollar.route) }) {
            Text("Ir a Dólar")
        }
    }
}
