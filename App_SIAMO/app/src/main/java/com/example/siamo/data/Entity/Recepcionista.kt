package com.example.siamo.data.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "recepcionistas",
    foreignKeys = [
        ForeignKey(
            entity = Empleado::class,
            parentColumns = ["idEmpleado"],
            childColumns = ["idEmpleado"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Recepcionista(
    @PrimaryKey(autoGenerate = true)
    val idRecepcionista: Int=0,
    val idEmpleado: Int
)
