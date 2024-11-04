package com.example.siamo

import android.app.Application
import com.example.siamo.data.AppContainer
import com.example.siamo.data.AppDataContainer

class SiamoApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Inicializa el contenedor de datos con esta aplicación
        container = AppDataContainer(this)
//        createInitialEmployee()
    }

//    private fun createInitialEmployee() {
//        val initialPersona = Persona(
//            tipoDoc = 1,
//            numDoc = "12345678",
//            nombres = "John",
//            apellidos = "Doe",
//            direccion = "123 Main St",
//            sexo = "M",
//            telefono = "1234567890",
//            correo = "john.doe@example.com"
//        )
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val personaId = container.personasRepository.insertPersona(initialPersona)
//            val initialEmployee = Empleado(
//                idPersona = personaId.toInt(),
//                fechaIngreso = "2023-01-01",
//                codEmpleado = 12345,
//                contrasenia = "password"
//            )
//            Log.d(initialEmployee.toString(), "createInitialEmployee: ")
//            container.empleadosRepository.insertEmpleado(initialEmployee)
//        }
//
//    }
}
