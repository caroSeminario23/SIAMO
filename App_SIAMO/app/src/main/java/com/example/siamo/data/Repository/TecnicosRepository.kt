package com.example.siamo.data.Repository

import com.example.siamo.data.Entity.Tecnico
import kotlinx.coroutines.flow.Flow

interface TecnicosRepository {
    fun getAllTecnicosStream(): Flow<List<Tecnico>>
    fun getTecnicoStream(id: Int): Flow<Tecnico?>
    suspend fun insertTecnico(tecnico: Tecnico)
    suspend fun updateTecnico(tecnico: Tecnico)
    suspend fun deleteTecnico(tecnico: Tecnico)
}