package com.example.siamo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siamo.ui.login.LoginDestination
import com.example.siamo.ui.login.SiamoLoginScreen
import com.example.siamo.ui.login.WelcomeDestination
import com.example.siamo.ui.login.WelcomeScreen
import com.example.siamo.ui.registerClient.RegisterDestination
import com.example.siamo.ui.registerClient.SiamoResgisterScreen
import com.example.siamo.ui.registroOST.BuscarClienteScreen

@Composable
fun SiamoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = WelcomeDestination.route,
        modifier = modifier
    ) {
        composable(WelcomeDestination.route) {
            WelcomeScreen(onWelcomeClick = {
                navController.navigate(LoginDestination.route)
            })
        }
        composable(LoginDestination.route) {
            SiamoLoginScreen(
                onNavigateUp = { navController.navigateUp()},
                navigateBack = { navController.popBackStack() },
                moveToRegister = { navController.navigate(BuscarClienteScreen.route) }
            )
        }
        composable(BuscarClienteScreen.route) {
            BuscarClienteScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() },
                moveToRegister = { navController.navigate(RegisterDestination.route) }
            )
        }
        composable(RegisterDestination.route) {
            SiamoResgisterScreen(
                moveToOstRegister = { },
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}