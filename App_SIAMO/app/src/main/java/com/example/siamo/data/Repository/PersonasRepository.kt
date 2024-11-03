package com.example.siamo.data.Repository

import com.example.siamo.data.Entity.Persona
import kotlinx.coroutines.flow.Flow

interface PersonasRepository {
    fun getAllPersonasStream(): Flow<List<Persona>>
    fun getPersonaStream(id: Int): Flow<Persona?>
    suspend fun insertPersona(persona: Persona)
    suspend fun updatePersona(persona: Persona)
    suspend fun deletePersona(persona: Persona)
}