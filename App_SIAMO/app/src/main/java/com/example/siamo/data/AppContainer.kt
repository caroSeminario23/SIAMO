package com.example.siamo.data

import android.content.Context
import com.example.siamo.data.OfflineRepository.OfflineClientesRepository
import com.example.siamo.data.OfflineRepository.OfflineEmpleadosRepository
import com.example.siamo.data.OfflineRepository.OfflinePersonasRepository
import com.example.siamo.data.OfflineRepository.OfflineRecepcionistasRepository
import com.example.siamo.data.OfflineRepository.OfflineTecnicosRepository
import com.example.siamo.data.Repository.ClientesRepository
import com.example.siamo.data.Repository.EmpleadosRepository
import com.example.siamo.data.Repository.PersonasRepository
import com.example.siamo.data.Repository.RecepcionistasRepository
import com.example.siamo.data.Repository.TecnicosRepository

interface AppContainer {
    val personasRepository: PersonasRepository
    val empleadosRepository: EmpleadosRepository
    val tecnicosRepository: TecnicosRepository
    val recepcionistasRepository: RecepcionistasRepository
    val clientesRepository: ClientesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val personasRepository: PersonasRepository by lazy {
        OfflinePersonasRepository(SiamoDatabase.getDatabase(context).personaDao())
    }

    override val empleadosRepository: EmpleadosRepository by lazy {
        OfflineEmpleadosRepository(SiamoDatabase.getDatabase(context).empleadoDao())
    }

    override val tecnicosRepository: TecnicosRepository by lazy {
        OfflineTecnicosRepository(SiamoDatabase.getDatabase(context).tecnicoDao())
    }

    override val recepcionistasRepository: RecepcionistasRepository by lazy {
        OfflineRecepcionistasRepository(SiamoDatabase.getDatabase(context).recepcionistaDao())
    }

    override val clientesRepository: ClientesRepository by lazy {
        OfflineClientesRepository(SiamoDatabase.getDatabase(context).clienteDao())
    }
}