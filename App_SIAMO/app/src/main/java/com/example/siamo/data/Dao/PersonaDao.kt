package com.example.siamo.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.siamo.data.Entity.Persona
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao {
    @Query("SELECT * from personas ORDER BY nombres ASC")
    fun getAllPersona(): Flow<List<Persona>>

    @Query("SELECT * from personas WHERE idPersona = :id")
    fun getPersona(id: Int): Flow<Persona>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(persona: Persona): Long

    @Update
    suspend fun update(persona: Persona)

    @Delete
    suspend fun delete(persona: Persona)
}