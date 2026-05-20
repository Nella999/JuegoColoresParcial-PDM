package com.example.juegocoloresparcial.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.juegocoloresparcial.ui.screens.*
import com.example.juegocoloresparcial.viewmodel.GameViewModel
@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val viewModel: GameViewModel = viewModel()

    NavHost( //contenedor de pantallas
        navController = navController,
        startDestination = "welcome" //muestra welcomescreen
    ) {

        composable("welcome") {//ruta
            WelcomeScreen(navController)
        }

        composable("game") {//muestra gamescreen
            GameScreen(navController, viewModel)
        }

        composable("result") {//muestra resultscreen
            ResultScreen(navController, viewModel)
        }
    }
}
