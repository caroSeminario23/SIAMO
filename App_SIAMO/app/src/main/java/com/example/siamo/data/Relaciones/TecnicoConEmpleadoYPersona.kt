package com.example.siamo.data.Relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.example.siamo.data.Entity.Empleado
import com.example.siamo.data.Entity.Tecnico

data class TecnicoConEmpleadoYPersona(
    @Embedded val tecnico: Tecnico,
    @Relation(
        entity = Empleado::class,
        parentColumn = "idEmpleado",
        entityColumn = "idEmpleado"
    )
    val empleadoConPersona: EmpleadoConPersona
)
