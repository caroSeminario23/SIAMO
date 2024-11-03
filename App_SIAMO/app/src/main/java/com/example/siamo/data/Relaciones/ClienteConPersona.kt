package com.example.siamo.data.Relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.example.siamo.data.Entity.Cliente
import com.example.siamo.data.Entity.Persona

data class ClienteConPersona(
    @Embedded
    val cliente: Cliente,
    @Relation(
        parentColumn = "idPersona",
        entityColumn = "idPersona"
    )
    val persona: Persona
)
