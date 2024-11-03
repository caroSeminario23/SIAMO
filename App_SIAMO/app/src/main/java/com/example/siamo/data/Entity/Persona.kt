package com.example.siamo.data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personas")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val idPersona: Int=0,
    val tipoDoc: Int,
    val numDoc: String,
    val nombres: String,
    val apellidos: String,
    val direccion: String,
    val sexo: Char,
    val telefono: String,
    val correo: String
)