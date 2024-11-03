package com.example.siamo.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.siamo.data.Entity.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {
    @Query("SELECT * from clientes ORDER BY idCliente ASC")
    fun getAllClientes(): Flow<List<Cliente>>

    @Query("SELECT * from clientes WHERE idCliente = :id")
    fun getCliente(id: Int): Flow<Cliente>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cliente: Cliente)

    @Update
    suspend fun update(cliente: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)
}