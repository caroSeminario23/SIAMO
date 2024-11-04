package com.example.siamo.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.siamo.SiamoApplication
import com.example.siamo.ui.login.SiamoLoginViewModel
import com.example.siamo.ui.registerClient.RegisterClientViewModel
import com.example.siamo.ui.registroOST.BuscarClienteViewModel

/**
 * Proveedor de ViewModel para la aplicación Siamo.
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Inicializador para SiamoLoginViewModel
        initializer {
            SiamoLoginViewModel(
                empleadosRepository = inventoryApplication().container.empleadosRepository
            )
        }

        // Inicializador para RegisterClientViewModel
        initializer {
            RegisterClientViewModel(
                clientesRepository = inventoryApplication().container.clientesRepository,
                personasRepository = inventoryApplication().container.personasRepository
            )
        }

        // Inicializador para BuscarClienteViewModel
        initializer {
            BuscarClienteViewModel(
                clientesRepository = inventoryApplication().container.clientesRepository
            )
        }
    }
}

/**
 * Función de extensión para obtener la instancia de la aplicación.
 */
fun CreationExtras.inventoryApplication(): SiamoApplication=
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SiamoApplication)
