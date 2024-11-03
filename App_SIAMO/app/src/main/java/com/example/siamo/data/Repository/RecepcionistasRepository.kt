package com.example.siamo.data.Repository

import com.example.siamo.data.Entity.Recepcionista
import kotlinx.coroutines.flow.Flow

interface RecepcionistasRepository {
    fun getAllRecepcionistasStream(): Flow<List<Recepcionista>>
    fun getRecepcionistaStream(id: Int): Flow<Recepcionista?>
    suspend fun insertRecepcionista(recepcionista: Recepcionista)
    suspend fun updateRecepcionista(recepcionista: Recepcionista)
    suspend fun deleteRecepcionista(recepcionista: Recepcionista)
}