package com.example.siamo.data.Relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.example.siamo.data.Entity.Empleado
import com.example.siamo.data.Entity.Recepcionista

data class RecepcionistaConEmpleadoYPersona(
    @Embedded
    val recepcionista: Recepcionista,
    @Relation(
        entity = Empleado::class,
        parentColumn = "idEmpleado",
        entityColumn = "idEmpleado"
    )
    val empleadoConPersona: EmpleadoConPersona
)
