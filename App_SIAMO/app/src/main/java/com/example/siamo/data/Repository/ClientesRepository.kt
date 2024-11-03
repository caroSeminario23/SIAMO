package com.example.siamo.data.Repository

import com.example.siamo.data.Entity.Cliente
import kotlinx.coroutines.flow.Flow

interface ClientesRepository {
    fun getAllClientesStream(): Flow<List<Cliente>>
    fun getClienteStream(id: Int): Flow<Cliente?>
    suspend fun insertCliente(cliente: Cliente)
    suspend fun updateCliente(cliente: Cliente)
    suspend fun deleteCliente(cliente: Cliente)
}