package com.xlorit.template.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xlorit.template.feature.auth.presentation.screen.LoginScreen

@Composable
fun AppNavHost(
    startDestination: String = Route.Login.path
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Route.Login.path) {
            LoginScreen(
                onSuccess = {
                    navController.navigate(Route.Home.path) {
                        popUpTo(Route.Login.path) { inclusive = true }
                    }
                }
            )
        }

        composable(Route.Home.path) {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    TODO("Not yet implemented")
}
