package com.example.siamo.data.local

import com.example.siamo.data.Empleado

object LocalEmpleadoDataProvider {
    val defaultEmpleado = Empleado(
        id = -1,
        codigoEmpleado = -1,
        contraseña = "default_password"
    )

    val empleados = listOf(
        Empleado(
            id = 1,
            codigoEmpleado = 1001,
            contraseña = "password123"
        ),
        Empleado(
            id = 2,
            codigoEmpleado = 1002,
            contraseña = "abc12345"
        ),
        Empleado(
            id = 3,
            codigoEmpleado = 1003,
            contraseña = "securePass!"
        )
    )
    /**
     * Verifica si existe un empleado con el código y contraseña proporcionados.
     */
    fun isValidEmployee(codigoEmpleado: Int, contraseña: String): Boolean {
        return empleados.any {
            it.codigoEmpleado == codigoEmpleado && it.contraseña == contraseña
        }
    }

    /**
     * Devuelve el empleado con el id proporcionado.
     */
    fun getEmpleadoPorId(id: Int): Empleado {
        return empleados.firstOrNull { it.id == id } ?: defaultEmpleado
    }
}
