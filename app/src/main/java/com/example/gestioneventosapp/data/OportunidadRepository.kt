package com.example.gestioneventosapp.data

import com.example.gestioneventosapp.data.entity.OportunidadEntity
import kotlinx.coroutines.flow.Flow

interface OportunidadRepository {
    fun obtenerTodas(): Flow<List<OportunidadEntity>>
    suspend fun obtenerPorId(id: Long): OportunidadEntity?
    fun obtenerPorEstado(estado: String): Flow<List<OportunidadEntity>>
    suspend fun insertar(oportunidad: OportunidadEntity)
    suspend fun actualizar(oportunidad: OportunidadEntity)
}