package com.example.siamo.data.Relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.example.siamo.data.Entity.Empleado
import com.example.siamo.data.Entity.Persona

data class EmpleadoConPersona(
    @Embedded val empleado: Empleado,
    @Relation(
        parentColumn = "idPersona",
        entityColumn = "idPersona"
    )
    val persona: Persona
)
