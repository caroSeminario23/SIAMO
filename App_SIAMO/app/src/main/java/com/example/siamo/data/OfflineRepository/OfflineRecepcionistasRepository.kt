package com.example.siamo.data.OfflineRepository

import com.example.siamo.data.Dao.RecepcionistaDao
import com.example.siamo.data.Entity.Recepcionista
import com.example.siamo.data.Repository.RecepcionistasRepository
import kotlinx.coroutines.flow.Flow

class OfflineRecepcionistasRepository(private val recepcionistaDao: RecepcionistaDao) : RecepcionistasRepository {
    override fun getAllRecepcionistasStream(): Flow<List<Recepcionista>> = recepcionistaDao.getAllRecepcionistas()

    override fun getRecepcionistaStream(id: Int): Flow<Recepcionista?> = recepcionistaDao.getRecepcionista(id)

    override suspend fun insertRecepcionista(recepcionista: Recepcionista) = recepcionistaDao.insert(recepcionista)

    override suspend fun updateRecepcionista(recepcionista: Recepcionista) = recepcionistaDao.update(recepcionista)

    override suspend fun deleteRecepcionista(recepcionista: Recepcionista) = recepcionistaDao.delete(recepcionista)
}