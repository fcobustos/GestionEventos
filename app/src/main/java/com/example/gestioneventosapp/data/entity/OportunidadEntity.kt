package com.example.gestioneventosapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "oportunidades")
data class OportunidadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    val tipoOportunidad: String,
    val descripcion: String,
    val fuenteInformacion: String,
    val fechaDeteccion: String,
    val responsableInterno: String,
    val prioridad: String,
    val potencialComercial: String,
    val estado: String,
    val comuna: String,
    val region: String,
    val publicoEsperado: Int,
    val observaciones: String = ""
)