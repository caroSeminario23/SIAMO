package com.example.siamo.data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "tecnicos",
    foreignKeys = [
        ForeignKey(
            entity = Empleado::class,
            parentColumns = ["idEmpleado"],
            childColumns = ["idEmpleado"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Tecnico(
    @PrimaryKey(autoGenerate = true)
    val idTecnico: Int=0,
    val idEmpleado: Int
)
