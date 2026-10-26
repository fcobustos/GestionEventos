package com.example.gestioneventosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestioneventosapp.data.AppDatabase
import com.example.gestioneventosapp.data.OportunidadRepositoryImpl
import com.example.gestioneventosapp.navigation.AppNavigation
import com.example.gestioneventosapp.ui.theme.GestionEventosAppTheme
import com.example.gestioneventosapp.ui.viewmodel.OportunidadViewModel
import com.example.gestioneventosapp.ui.viewmodel.OportunidadViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicialización del Singleton de Room y el Repositorio
        val database = AppDatabase.getDatabase(this)
        val repository = OportunidadRepositoryImpl(database.oportunidadDao())
        val factory = OportunidadViewModelFactory(repository)

        setContent {
            GestionEventosAppTheme {
                // Se especifica explícitamente el tipo del ViewModel para evitar errores de inferencia
                val viewModel: OportunidadViewModel = viewModel(
                    modelClass = OportunidadViewModel::class.java,
                    factory = factory
                )
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}