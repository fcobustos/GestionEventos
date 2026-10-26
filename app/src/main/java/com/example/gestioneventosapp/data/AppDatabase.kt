package com.example.gestioneventosapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gestioneventosapp.data.dao.OportunidadDao
import com.example.gestioneventosapp.data.entity.OportunidadEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [OportunidadEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun oportunidadDao(): OportunidadDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gestion_eventos_db"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        poblarDatosIniciales(database.oportunidadDao())
                    }
                }
            }

            suspend fun poblarDatosIniciales(dao: OportunidadDao) {
                val datosFicticios = listOf(
                    OportunidadEntity(
                        nombre = "Feria de Beneficios Santiago 2026",
                        tipoOportunidad = "Feria empresarial",
                        descripcion = "Stand para la promoción de servicios de salud dental a colaboradores.",
                        fuenteInformacion = "Redes Sociales",
                        fechaDeteccion = "2026-10-01",
                        responsableInterno = "Juan Pérez (Ficticio)",
                        prioridad = "Alta",
                        potencialComercial = "Alto",
                        estado = "Contacto realizado",
                        comuna = "Santiago",
                        region = "Metropolitana",
                        publicoEsperado = 1200,
                        observaciones = "Pendiente confirmar disponibilidad de stand principal."
                    ),
                    OportunidadEntity(
                        nombre = "Jornada de Salud Municipal Puente Alto",
                        tipoOportunidad = "Actividad municipal",
                        descripcion = "Activación corporativa orientada a la comunidad y trabajadores municipales.",
                        fuenteInformacion = "Sitio Web Municipal",
                        fechaDeteccion = "2026-10-15",
                        responsableInterno = "Maria González (Ficticia)",
                        prioridad = "Media",
                        potencialComercial = "Medio",
                        estado = "Contacto pendiente",
                        comuna = "Puente Alto",
                        region = "Metropolitana",
                        publicoEsperado = 800,
                        observaciones = "Enviar propuesta antes del viernes."
                    )
                )
                dao.insertarTodas(datosFicticios)
            }
        }
    }
}