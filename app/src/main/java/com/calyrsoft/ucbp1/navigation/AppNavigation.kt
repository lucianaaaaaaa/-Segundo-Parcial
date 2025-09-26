package com.calyrsoft.ucbp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calyrsoft.ucbp1.features.dollar.presentation.DollarScreen
import com.calyrsoft.ucbp1.features.github.presentation.GithubScreen
import com.calyrsoft.ucbp1.features.movies.presentation.MovieScreen
import com.calyrsoft.ucbp1.features.profile.application.ProfileScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.PopularMovies.route
    ) {
        composable(Screen.Github.route) {
            GithubScreen(navController = navController)
        }
        composable(Screen.Dollar.route) {
            DollarScreen(navController = navController)
        }
        composable(Screen.PopularMovies.route) {
            MovieScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(Screen.Home.route) {
        }
    }
}

