package com.example.siamo.data.OfflineRepository

import com.example.siamo.data.Dao.ClienteDao
import com.example.siamo.data.Entity.Cliente
import com.example.siamo.data.Relaciones.ClienteConPersona
import com.example.siamo.data.Repository.ClientesRepository
import kotlinx.coroutines.flow.Flow

class OfflineClientesRepository(private val clienteDao: ClienteDao) : ClientesRepository {
    override fun getAllClientesStream(): Flow<List<Cliente>> = clienteDao.getAllClientes()

    override fun getClienteStream(id: Int): Flow<Cliente?> = clienteDao.getCliente(id)

    override fun getClientePorNumeroDocumento(numeroDocumento: String): Flow<ClienteConPersona?> = clienteDao.getClientePorNumeroDocumento(numeroDocumento)

    override suspend fun insertCliente(cliente: Cliente) = clienteDao.insert(cliente)

    override suspend fun deleteCliente(cliente: Cliente) = clienteDao.delete(cliente)

    override suspend fun updateCliente(cliente: Cliente) = clienteDao.update(cliente)
}