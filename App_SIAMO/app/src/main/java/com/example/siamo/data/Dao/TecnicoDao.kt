package com.example.siamo.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.siamo.data.Entity.Tecnico
import com.example.siamo.data.Relaciones.TecnicoConEmpleadoYPersona
import kotlinx.coroutines.flow.Flow

@Dao
interface TecnicoDao {
    @Query("SELECT * from tecnicos ORDER BY idTecnico ASC")
    fun getAllTecnicos(): Flow<List<Tecnico>>

    @Query("SELECT * from tecnicos WHERE idTecnico = :id")
    fun getTecnico(id: Int): Flow<Tecnico>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tecnico: Tecnico)

    @Update
    suspend fun update(tecnico: Tecnico)

    @Delete
    suspend fun delete(tecnico: Tecnico)

    @Transaction
    @Query("SELECT * from tecnicos ORDER BY idTecnico ASC")
    fun getTecnicosConEmpleadoYPersona(): Flow<List<TecnicoConEmpleadoYPersona>>

    @Transaction
    @Query("SELECT * from tecnicos WHERE idTecnico = :id")
    fun getTecnicoConEmpleadoYPersona(id: Int): Flow<TecnicoConEmpleadoYPersona>
}