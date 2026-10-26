package com.example.gestioneventosapp.data

import com.example.gestioneventosapp.data.dao.OportunidadDao
import com.example.gestioneventosapp.data.entity.OportunidadEntity
import kotlinx.coroutines.flow.Flow

class OportunidadRepositoryImpl(
    private val dao: OportunidadDao
) : OportunidadRepository {

    override fun obtenerTodas(): Flow<List<OportunidadEntity>> = dao.obtenerTodas()

    override suspend fun obtenerPorId(id: Long): OportunidadEntity? = dao.obtenerPorId(id)

    override fun obtenerPorEstado(estado: String): Flow<List<OportunidadEntity>> = dao.obtenerPorEstado(estado)

    override suspend fun insertar(oportunidad: OportunidadEntity) = dao.insertar(oportunidad)

    override suspend fun actualizar(oportunidad: OportunidadEntity) = dao.actualizar(oportunidad)
}