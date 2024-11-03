package com.example.siamo.data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

/*data class Cliente(
    val nombres: String,
    val apellidos: String,
    val tipo_doc: Boolean,
    val nro_doc: Int,
    val direccion: String,
    val correo: String,
    val telefono: Int,
    val sexo: Char
)*/

@Entity(
    tableName = "clientes",
    foreignKeys = [
        ForeignKey(
            entity = Persona::class,
            parentColumns = ["idPersona"],
            childColumns = ["idPersona"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val idCliente: Int=0,
    val idPersona: Int
)