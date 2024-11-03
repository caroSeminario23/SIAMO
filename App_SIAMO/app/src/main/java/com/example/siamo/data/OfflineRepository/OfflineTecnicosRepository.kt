package com.example.siamo.data.OfflineRepository

import com.example.siamo.data.Dao.TecnicoDao
import com.example.siamo.data.Entity.Tecnico
import com.example.siamo.data.Repository.TecnicosRepository
import kotlinx.coroutines.flow.Flow

class OfflineTecnicosRepository(private val tecnicoDao: TecnicoDao) : TecnicosRepository {
    override fun getAllTecnicosStream(): Flow<List<Tecnico>> = tecnicoDao.getAllTecnicos()

    override fun getTecnicoStream(id: Int): Flow<Tecnico?> = tecnicoDao.getTecnico(id)

    override suspend fun insertTecnico(tecnico: Tecnico) = tecnicoDao.insert(tecnico)

    override suspend fun updateTecnico(tecnico: Tecnico) = tecnicoDao.update(tecnico)

    override suspend fun deleteTecnico(tecnico: Tecnico) = tecnicoDao.delete(tecnico)
}