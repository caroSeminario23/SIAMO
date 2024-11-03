package com.example.siamo.data.OfflineRepository

import com.example.siamo.data.Dao.PersonaDao
import com.example.siamo.data.Entity.Persona
import com.example.siamo.data.Repository.PersonasRepository
import kotlinx.coroutines.flow.Flow

class OfflinePersonasRepository(private val personaDao: PersonaDao) : PersonasRepository {
    override fun getAllPersonasStream(): Flow<List<Persona>> = personaDao.getAllPersona()

    override fun getPersonaStream(id: Int): Flow<Persona?> = personaDao.getPersona(id)

    override suspend fun insertPersona(persona: Persona) = personaDao.insert(persona)

    override suspend fun updatePersona(persona: Persona) = personaDao.update(persona)

    override suspend fun deletePersona(persona: Persona) = personaDao.delete(persona)
}