package com.example.gestioneventosapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestioneventosapp.data.entity.OportunidadEntity
import com.example.gestioneventosapp.ui.viewmodel.OportunidadViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioOportunidadScreen(
    viewModel: OportunidadViewModel,
    onVolver: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var tipoOportunidad by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fuenteInformacion by remember { mutableStateOf("") }
    var responsableInterno by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("Media") }
    var estado by remember { mutableStateOf("Contacto pendiente") }
    var comuna by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("") }
    var publicoEsperadoText by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Oportunidad") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del Evento/Convenio") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = tipoOportunidad,
                onValueChange = { tipoOportunidad = it },
                label = { Text("Tipo (ej: Feria empresarial, Actividad municipal)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = comuna,
                onValueChange = { comuna = it },
                label = { Text("Comuna") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = region,
                onValueChange = { region = it },
                label = { Text("Región") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = responsableInterno,
                onValueChange = { responsableInterno = it },
                label = { Text("Responsable Interno (Ficticio)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = publicoEsperadoText,
                onValueChange = { publicoEsperadoText = it },
                label = { Text("Público Esperado (Número)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = observaciones,
                onValueChange = { observaciones = it },
                label = { Text("Observaciones") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nombre.isNotBlank()) {
                        val nuevaOportunidad = OportunidadEntity(
                            nombre = nombre,
                            tipoOportunidad = tipoOportunidad.ifBlank { "General" },
                            descripcion = descripcion,
                            fuenteInformacion = fuenteInformacion.ifBlank { "Interna" },
                            fechaDeteccion = "2026-10-25",
                            responsableInterno = responsableInterno.ifBlank { "Sin Asignar" },
                            prioridad = prioridad,
                            potencialComercial = "Alto",
                            estado = estado,
                            comuna = comuna.ifBlank { "Santiago" },
                            region = region.ifBlank { "Metropolitana" },
                            publicoEsperado = publicoEsperadoText.toIntOrNull() ?: 0,
                            observaciones = observaciones
                        )
                        viewModel.guardarOportunidad(nuevaOportunidad)
                        onVolver()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Oportunidad")
            }
        }
    }
}