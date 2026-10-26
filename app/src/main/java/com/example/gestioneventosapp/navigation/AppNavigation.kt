package com.example.gestioneventosapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestioneventosapp.ui.screens.FormularioOportunidadScreen
import com.example.gestioneventosapp.ui.screens.ListaOportunidadesScreen
import com.example.gestioneventosapp.ui.viewmodel.OportunidadViewModel

sealed class Screen(val route: String) {
    object Lista : Screen("lista")
    object Formulario : Screen("formulario")
}

@Composable
fun AppNavigation(viewModel: OportunidadViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Lista.route
    ) {
        composable(Screen.Lista.route) {
            ListaOportunidadesScreen(
                viewModel = viewModel,
                onAgregarClick = { navController.navigate(Screen.Formulario.route) }
            )
        }
        composable(Screen.Formulario.route) {
            FormularioOportunidadScreen(
                viewModel = viewModel,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}