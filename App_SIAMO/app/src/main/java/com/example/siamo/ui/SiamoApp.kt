package com.example.siamo.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SiamoApp() {
    val navController = rememberNavController()
    val loginViewModel: SiamoViewModel = viewModel() // Obtener la instancia del ViewModel

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(onWelcomeClick = {
                navController.navigate("login")
            })
        }
        composable("login") {
            SiamoLoginScreen(
                viewModel = loginViewModel,
                onLogin = { employeeCode, password ->
                    loginViewModel.onLogin(employeeCode, password)
                    // No navegamos a otra pantalla; solo mostramos mensajes de éxito/error
                },
                loginSuccess = loginViewModel.loginSuccess.value,
                loginError = loginViewModel.loginError.value
            )
        }
    }
}

