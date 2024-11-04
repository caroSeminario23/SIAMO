package com.example.siamo.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.siamo.data.Entity.Cliente
import com.example.siamo.data.Relaciones.ClienteConPersona
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

    @Transaction
    @Query("SELECT * from clientes ORDER BY idCliente ASC")
    fun getClientesConPersona(): Flow<List<ClienteConPersona>>

    @Transaction
    @Query("SELECT * from clientes WHERE idCliente = :id")
    fun getClienteConPersona(id: Int): Flow<ClienteConPersona>

    @Transaction
    @Query("""
    SELECT * FROM clientes 
    INNER JOIN personas ON clientes.idPersona = personas.idPersona 
    WHERE personas.numDoc = :numeroDocumento
""")
    fun getClientePorNumeroDocumento(numeroDocumento: String): Flow<ClienteConPersona?>

}