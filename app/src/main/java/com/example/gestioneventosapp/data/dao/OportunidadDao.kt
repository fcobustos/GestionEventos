package com.example.gestioneventosapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gestioneventosapp.data.entity.OportunidadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OportunidadDao {

    @Query("SELECT * FROM oportunidades ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<OportunidadEntity>>

    @Query("SELECT * FROM oportunidades WHERE id = :id")
    suspend fun obtenerPorId(id: Long): OportunidadEntity?

    @Query("SELECT * FROM oportunidades WHERE estado = :estado")
    fun obtenerPorEstado(estado: String): Flow<List<OportunidadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(oportunidad: OportunidadEntity)

    @Update
    suspend fun actualizar(oportunidad: OportunidadEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodas(oportunidades: List<OportunidadEntity>)
}