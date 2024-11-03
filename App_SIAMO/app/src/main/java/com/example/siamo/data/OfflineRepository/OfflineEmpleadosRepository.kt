package com.example.siamo.data.OfflineRepository

import com.example.siamo.data.Dao.EmpleadoDao
import com.example.siamo.data.Entity.Empleado
import com.example.siamo.data.Repository.EmpleadosRepository
import kotlinx.coroutines.flow.Flow

class OfflineEmpleadosRepository(private val empleadoDao: EmpleadoDao) : EmpleadosRepository {
    override fun getAllEmpleadosStream(): Flow<List<Empleado>> = empleadoDao.getAllEmpleados()

    override fun getEmpleadoStream(id: Int): Flow<Empleado?> = empleadoDao.getEmpleado(id)

    override suspend fun insertEmpleado(empleado: Empleado) = empleadoDao.insert(empleado)

    override suspend fun deleteEmpleado(empleado: Empleado) = empleadoDao.delete(empleado)

    override suspend fun updateEmpleado(empleado: Empleado) = empleadoDao.update(empleado)
}