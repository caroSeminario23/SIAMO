package com.example.siamo.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.siamo.data.Entity.Recepcionista
import com.example.siamo.data.Relaciones.RecepcionistaConEmpleadoYPersona
import kotlinx.coroutines.flow.Flow

@Dao
interface RecepcionistaDao {
    @Query("SELECT * from recepcionistas ORDER BY idRecepcionista ASC")
    fun getAllRecepcionistas(): Flow<List<Recepcionista>>

    @Query("SELECT * from recepcionistas WHERE idRecepcionista = :id")
    fun getRecepcionista(id: Int): Flow<Recepcionista>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recepcionista: Recepcionista)

    @Update
    suspend fun update(recepcionista: Recepcionista)

    @Delete
    suspend fun delete(recepcionista: Recepcionista)

    @Transaction
    @Query("SELECT * from recepcionistas ORDER BY idRecepcionista ASC")
    fun getRecepcionistasConEmpleadoYPersona(): Flow<List<RecepcionistaConEmpleadoYPersona>>

    @Transaction
    @Query("SELECT * from recepcionistas WHERE idRecepcionista = :id")
    fun getRecepcionistaConEmpleadoYPersona(id: Int): Flow<RecepcionistaConEmpleadoYPersona>
}