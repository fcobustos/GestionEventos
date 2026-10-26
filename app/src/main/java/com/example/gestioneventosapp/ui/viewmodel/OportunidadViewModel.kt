package com.example.gestioneventosapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gestioneventosapp.data.OportunidadRepository
import com.example.gestioneventosapp.data.entity.OportunidadEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OportunidadViewModel(
    private val repository: OportunidadRepository
) : ViewModel() {

    val listaOportunidades: StateFlow<List<OportunidadEntity>> =
        repository.obtenerTodas()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun guardarOportunidad(oportunidad: OportunidadEntity) {
        viewModelScope.launch {
            if (oportunidad.id == 0L) {
                repository.insertar(oportunidad)
            } else {
                repository.actualizar(oportunidad)
            }
        }
    }
}

class OportunidadViewModelFactory(
    private val repository: OportunidadRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OportunidadViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OportunidadViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Class desconocida")
    }
}