package com.example.siamo.data.Repository

import com.example.siamo.data.Entity.Empleado
import kotlinx.coroutines.flow.Flow

interface EmpleadosRepository {
    fun getAllEmpleadosStream(): Flow<List<Empleado>>
    fun getEmpleadoStream(id: Int): Flow<Empleado?>
    suspend fun insertEmpleado(empleado: Empleado)
    suspend fun updateEmpleado(empleado: Empleado)
    suspend fun deleteEmpleado(empleado: Empleado)
}