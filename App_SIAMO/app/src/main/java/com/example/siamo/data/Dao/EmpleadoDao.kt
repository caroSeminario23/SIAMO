package com.example.siamo.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.siamo.data.Entity.Empleado
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpleadoDao {
    @Query("SELECT * from empleados ORDER BY idEmpleado ASC")
    fun getAllEmpleados(): Flow<List<Empleado>>

    /*@Query("SELECT empleados.idEmpleado, personas.nombres, personas.apellidos FROM empleados INNER JOIN personas ON empleados.idPersona = personas.idPersona ORDER BY empleados.idEmpleado ASC")
    fun getAllEmpleados(): Flow<List<Empleado>>*/

    @Query("SELECT * from empleados WHERE idEmpleado = :id")
    fun getEmpleado(id: Int): Flow<Empleado>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(empleado: Empleado)

    @Update
    suspend fun update(empleado: Empleado)

    @Delete
    suspend fun delete(empleado: Empleado)
}