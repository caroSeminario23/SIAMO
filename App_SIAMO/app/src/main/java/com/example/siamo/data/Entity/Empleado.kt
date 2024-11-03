package com.example.siamo.data.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "empleados",
    foreignKeys = [
        ForeignKey(
            entity = Persona::class,
            parentColumns = ["idPersona"],
            childColumns = ["idPersona"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Empleado(
    @PrimaryKey(autoGenerate = true)
    val idEmpleado: Int=0,
    val idPersona: Int,
    val fechaIngreso: String,
    val codEmpleado: Int,
    val contrasenia: String
)