package com.example.siamo.data

data class Cliente(
    val nombres: String,
    val apellidos: String,
    val tipo_doc: Boolean,
    val nro_doc: Int,
    val direccion: String,
    val correo: String,
    val telefono: Int,
    val sexo: Char
)